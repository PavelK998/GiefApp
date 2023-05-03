package ru.pakarp.GiefptAbitur.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.pakarp.GiefptAbitur.R
import ru.pakarp.GiefptAbitur.presentation.navigation.Screens
import ru.pakarp.GiefptAbitur.ui.theme.lazyItem
import ru.pakarp.GiefptAbitur.ui.theme.text


@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,) {




            LaunchedEffect(key1 = true) {
                delay(1000)
                navController.navigate(Screens.AuthScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true

                    }
                }

            }
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        ) {
            Image(
                painterResource(id = R.drawable.unnamed),
                contentDescription = "splash_image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Key to GIEF",
            fontSize = 25.sp,
            )

        }
    }


