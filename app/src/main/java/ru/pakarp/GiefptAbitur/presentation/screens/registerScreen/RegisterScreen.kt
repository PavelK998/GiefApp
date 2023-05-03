package ru.pakarp.GiefptAbitur.presentation.screens.registerScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.pakarp.GiefptAbitur.R
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import ru.pakarp.GiefptAbitur.presentation.viewModels.AuthViewModel
import ru.pakarp.GiefptAbitur.presentation.navigation.Screens
import ru.pakarp.GiefptAbitur.presentation.screens.authScreen.Loading
import ru.pakarp.GiefptAbitur.ui.theme.login_back
import ru.pakarp.GiefptAbitur.ui.theme.login_text_back
import ru.pakarp.GiefptAbitur.ui.theme.text


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun RegisterScreen(navController: NavController) {

    val mContext = LocalContext.current
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var surName by rememberSaveable {
        mutableStateOf("")
    }
    var passConfirm by rememberSaveable {
        mutableStateOf("")
    }
    val passLenght = 6

    var ifVisiblePass by rememberSaveable() {
        mutableStateOf(false)

    }

    val userModel = mutableStateOf(UserModel(null, email, password, name, surName))

    val viewModel = hiltViewModel<AuthViewModel>()

    Scaffold(modifier = Modifier.fillMaxSize()) {

        when (viewModel.registerStatus.value.result) {
            "" -> ""
            "loading" -> Loading()
            "success" -> {
                LaunchedEffect(true) {
                    navController.navigate(Screens.MainScreen.route)
                }
            }
            else -> {
                LaunchedEffect(true) {
                    Toast.makeText(mContext, viewModel.logInStatus.value.result, Toast.LENGTH_LONG)
                        .show()
                }
            }

        }
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            // alpha = 80f,
            painter = painterResource(id = R.drawable.register_background),
            contentDescription = "back"
        )
        Card(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = login_back
        ) {

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxSize(0.6f), contentAlignment = Alignment.BottomCenter) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {


                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = { Text(text = "Введите ваше имя", color = text) },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = login_text_back,
                            textColor = text
                        )
                    )

                    OutlinedTextField(
                        value = surName,
                        onValueChange = {
                            surName = it
                        },
                        label = { Text(text = "Введите вашу фамилию", color = text) },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = login_text_back,
                            textColor = text
                        )
                    )


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = { Text(text = "Введите email", color = text) },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = login_text_back,
                            textColor = text
                        )
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                           password = it
                        },
                        label = { Text(text = "Придумайте пароль", color = text) },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = login_text_back,
                            textColor = text
                        ),

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
                    OutlinedTextField(
                        value = passConfirm,
                        onValueChange = {
                            passConfirm = it
                        },
                        label = { Text(text = "Повторите пароль", color = text) },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = login_text_back,
                            textColor = text

                        ),


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

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                Card(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = login_text_back,
                    contentColor = login_text_back,
                    modifier = Modifier
                        .padding(50.dp)
                        .clickable {
                            if (name == "" || surName == "" || email == "" || password == "" || passConfirm == "") {
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
                            } else if (password != passConfirm) {
                                Toast
                                    .makeText(
                                        mContext,
                                        "Введенные пароли не совпадают",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()

                            } else {
                                viewModel.registerNewUser(user = userModel.value)
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
                        Text(text = "Зарегистрироваться", color = text)
                    }


                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Card(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    backgroundColor = login_text_back,
                    contentColor = login_text_back,
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.AuthScreen.route)
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(
                            top = 15.dp,
                            bottom = 15.dp,
                            start = 45.dp,
                            end = 45.dp
                        ),
                        fontSize = 18.sp,
                        color = text,
                        text = "Вход"
                    )
                }
            }


        }

    }

}

