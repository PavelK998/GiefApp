package ru.pakarp.GiefptAbitur.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.pakarp.GiefptAbitur.presentation.navigation.BottomNav
import ru.pakarp.GiefptAbitur.presentation.ui.DrawerBottom
import ru.pakarp.GiefptAbitur.presentation.ui.DrawerTop
import ru.pakarp.GiefptAbitur.presentation.ui.TopAppBarFun
import ru.pakarp.GiefptAbitur.ui.theme.background


@Composable
fun AspirantScreen(navController: NavController) {
    val mContext = LocalContext.current

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarFun(scaffoldState = scaffoldState) },
        bottomBar = { BottomNav(navController = navController) },
        drawerContent = {
            DrawerTop()
            DrawerBottom(navController = navController)

        }


    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    text = "Аспиранту"
                )
            }
        }

    }

}
