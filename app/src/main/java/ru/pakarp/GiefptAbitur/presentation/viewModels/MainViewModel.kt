package ru.pakarp.GiefptAbitur.presentation.viewModels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.pakarp.GiefptAbitur.domain.model.LazyColumnItem
import ru.pakarp.GiefptAbitur.domain.model.RowOfDirections
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import ru.pakarp.GiefptAbitur.presentation.useCase.MainUseCAse
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: MainUseCAse) : ViewModel() {

    private val _data = MutableLiveData<MutableList<RowOfDirections>>()
    val data: LiveData<MutableList<RowOfDirections>>
        get() = _data


    private val _model = MutableLiveData<UserModel>()
    val model: LiveData<UserModel>
        get() = _model

    var stateOfRODItem = mutableStateOf("1")

    private val _columnText = MutableLiveData<MutableList<LazyColumnItem>>()
    val columnText: LiveData<MutableList<LazyColumnItem>>
        get() = _columnText


    fun getData() {
        viewModelScope.launch {
            useCase.getAllData().let {
                _data.postValue(it)
            }
        }
    }

    fun getColumnText(pathName: String) {
        viewModelScope.launch {
            useCase.getColumnText(pathName = pathName).let {
                _columnText.postValue(it)

            }
        }
    }

    fun phoneCall(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + phone)
        ContextCompat.startActivity(context, intent, null)
    }


    fun urlView(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://$url")
        ContextCompat.startActivity(context, intent, null)

    }

    fun SendEmail(context: Context, email: String) {
        val intent = Intent(Intent.ACTION_SENDTO )
        intent.data = Uri.fromParts("mailto", email, null)
        ContextCompat.startActivity(context, intent, null)

    }

    fun mDelay (){
        viewModelScope.launch {
            delay(2000)
        }
    }




    fun getUserInfo (pathName: String) {
        viewModelScope.launch {
            useCase.getUserInfo(pathName = pathName).let {
               _model.postValue(it)
                Log.d("testGetUser", "getUserInfo: $it")
                Log.d("testGetUser", "LIVE DATA : $model ")

            }
        }
    }

}