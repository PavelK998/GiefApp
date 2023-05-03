package ru.pakarp.GiefptAbitur.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.pakarp.GiefptAbitur.domain.model.ResultModel
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import ru.pakarp.GiefptAbitur.presentation.useCase.AuthUseCase
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val useCase: AuthUseCase): ViewModel() {

    var registerStatus = mutableStateOf(ResultModel(""))


    fun registerNewUser (user: UserModel){
        viewModelScope.launch {
            useCase.registerNewUser(userModel = user).collect{
                registerStatus.value = it
            }
        }
    }

    var logInStatus = mutableStateOf(ResultModel(""))

    fun logIn (email: String, password: String){
        viewModelScope.launch {
            useCase.logIn(email = email, password = password).collect{
                logInStatus.value = it
            }
        }
    }


}