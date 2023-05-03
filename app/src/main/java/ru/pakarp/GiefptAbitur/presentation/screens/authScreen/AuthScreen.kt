package ru.pakarp.GiefptAbitur.presentation.screens.authScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.pakarp.GiefptAbitur.presentation.viewModels.MainViewModel
import ru.pakarp.GiefptAbitur.presentation.navigation.Screens

private lateinit var auth: FirebaseAuth
@Composable
fun AuthScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    auth= Firebase.auth
    if (auth.currentUser!= null) {



        LaunchedEffect(true) {
            //viewModel.getUserInfo(pathName = auth.currentUser!!.uid)
            navController.navigate(Screens.MainScreen.route){
                popUpTo(Screens.AuthScreen.route){
                    inclusive = true
                }
            }
        }
    }else {
        AuthItem(navController = navController)
    }


}
