package ru.pakarp.GiefptAbitur.presentation.screens.authScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.pakarp.GiefptAbitur.R
import ru.pakarp.GiefptAbitur.presentation.viewModels.AuthViewModel
import ru.pakarp.GiefptAbitur.presentation.navigation.Screens
import ru.pakarp.GiefptAbitur.ui.theme.appBar
import ru.pakarp.GiefptAbitur.ui.theme.login_back
import ru.pakarp.GiefptAbitur.ui.theme.login_text_back
import ru.pakarp.GiefptAbitur.ui.theme.text


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthItem(navController: NavController) {
    val mContext = LocalContext.current

    var email by rememberSaveable{
        mutableStateOf("")
    }
    var password by rememberSaveable{
        mutableStateOf("")
    }
    val viewModel = hiltViewModel<AuthViewModel>()

    var ifVisiblePass by rememberSaveable() {
        mutableStateOf(false)

    }

    val passLenght = 6


    Scaffold() {
        when (viewModel.logInStatus.value.result) {
            "" -> ""
            "loading" -> Loading()
            "success" -> {
                LaunchedEffect(true){
                    navController.navigate(Screens.MainScreen.route)
                }
            }
            else -> {
                LaunchedEffect(true){
                    Toast.makeText(mContext, viewModel.logInStatus.value.result, Toast.LENGTH_LONG).show()
                }
            }

        }
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
           // alpha = 80f,
            painter = painterResource(id = R.drawable.login_background), contentDescription = "back")
        Card(
            modifier = Modifier.fillMaxSize(),
        backgroundColor = login_back) {

        }
        Column(
            modifier = Modifier.fillMaxSize(),
          ) {


                Box(modifier = Modifier.fillMaxSize(0.6f),
                    contentAlignment = Alignment.BottomStart) {


                Card(

                    //elevation = 10.dp,
                    shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                    backgroundColor = login_text_back,
                    contentColor = login_text_back

                ) {

                    Column(

                        horizontalAlignment = Alignment.Start
                    ) {
                        TextField(

                            value = email,
                            onValueChange = {
                                email = it
                            },
                            label = { Text(text = "Логин", color = text) },
                            shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                            textStyle = TextStyle(fontSize = 17.sp),
                            colors = TextFieldDefaults.textFieldColors(textColor = text)
                        )
                        TextField(
                            modifier = Modifier.padding(top = 10.dp),
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = { Text(text = "Пароль", color = text) },
                            shape = RoundedCornerShape(bottomEnd = 50.dp),
                            textStyle = TextStyle(fontSize = 17.sp),
                            colors = TextFieldDefaults.textFieldColors(textColor = text),
                            trailingIcon = {
                                if (!ifVisiblePass){
                                    Icon(
                                        modifier = Modifier.clickable {
                                            ifVisiblePass = !ifVisiblePass
                                        },
                                        tint = text,
                                        painter = painterResource(id = R.drawable.visible_password) ,
                                        contentDescription = "visible_pass")
                                } else {
                                    Icon(
                                        modifier = Modifier.clickable {
                                            ifVisiblePass = !ifVisiblePass
                                        },
                                        tint = text,
                                        painter = painterResource(id = R.drawable.password_not_visible) ,
                                        contentDescription = "not_visible_pass")
                                }

                            },
                            visualTransformation = if (ifVisiblePass){
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )
                    }

                }

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
            contentAlignment = Alignment.TopCenter) {

                Column() {

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center) {
                    Card(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = login_text_back,
                        contentColor = login_text_back,
                        modifier = Modifier
                            .padding(30.dp)
                            .clickable {
                                if (email == "" || password == "") {
                                    Toast
                                        .makeText(
                                            mContext,
                                            "Пожалуйста заполните все поля",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else if (password.length < passLenght) {
                                    Toast
                                        .makeText(
                                            mContext,
                                            "Пароль не может быть меньше 6 символов",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    viewModel.logIn(email = email, password = password)
                                }
                            }
                            .height(50.dp)
                            .width(300.dp),

                        ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Войти", color = text)
                        }


                    }
                }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End) {
                        Text(
                            modifier = Modifier.padding(end = 15.dp),
                            fontSize = 18.sp,
                            color = text,
                            text = "Забыли пароль?")

                    }
                    Box( modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter) {
                        
                        Card(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                            backgroundColor = login_text_back,
                            contentColor = login_text_back,
                            modifier = Modifier.clickable {
                                 navController.navigate(Screens.RegisterScreen.route)
                                 }
                        ) {
                            Text(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp, start = 25.dp, end = 25.dp),
                                fontSize = 18.sp,
                                color = text,
                                text = "Регистрация")
                        }


                    }



                }
            }


        }
    }
}










@Composable
fun Loading() {
    Card(
        modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            CircularProgressIndicator()

        }

    }

}
