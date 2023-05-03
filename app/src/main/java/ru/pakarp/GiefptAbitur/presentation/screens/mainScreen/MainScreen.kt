package ru.pakarp.GiefptAbitur.presentation.screens.mainScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import ru.pakarp.GiefptAbitur.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.pakarp.GiefptAbitur.presentation.viewModels.MainViewModel
import ru.pakarp.GiefptAbitur.presentation.navigation.BottomNav
import ru.pakarp.GiefptAbitur.presentation.ui.*
import ru.pakarp.GiefptAbitur.ui.theme.background

private lateinit var auth: FirebaseAuth
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    auth = Firebase.auth



    val mContext = LocalContext.current

    val scaffoldState = rememberScaffoldState()



    val viewModel = hiltViewModel<MainViewModel>()

    LaunchedEffect(true){
        viewModel.getData()

    }




    val user = viewModel.model.observeAsState().value


    val idFromRowOfDirections = viewModel.stateOfRODItem.value


    Log.d("RowOfDirectionsItem", "MainScreenId: $idFromRowOfDirections")

    val list = viewModel.data.observeAsState().value

    Log.d("MainScreen", "MainScreen: $list")




            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    TopAppBarFun(
                        scaffoldState = scaffoldState,

                    )
                },
                bottomBar = { BottomNav(navController = navController) },
                drawerContent = {
                    DrawerTop()
                    DrawerBottom(navController = navController)

                }
            ) {
                when (list) {
                    null -> load()
                    else ->


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
                            text = "Абитуриенту"
                        )


                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp, start = 20.dp, top = 20.dp, bottom = 7.dp)) {


                        Image(
                            painter = painterResource(id = R.drawable.phone_vector),
                            contentDescription = "phone"
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable {
                                    viewModel.phoneCall(
                                        context = mContext,
                                        phone = "+7 (813) 71 413-21"
                                    )
                                },
                            text = "+7 (813) 71 413-21",
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.Justify
                        )
                    }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)) {

                            Image(
                                painter = painterResource(id = R.drawable.baseline_email_24),
                                contentDescription = "email"
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clickable {
                                        viewModel.SendEmail(
                                            context = mContext,
                                            email = "abiturient@gief.ru"
                                        )
                                    },
                                text = "abiturient@gief.ru",
                                textDecoration = TextDecoration.Underline,
                                textAlign = TextAlign.Justify
                            )
                        }


                    }
                    Column(modifier = Modifier.fillMaxWidth()) {


                    }
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (list != null) {
                            list.forEach { item ->
                                RowOfDirectionsItem(rowOfDirections = item)
                            }
                        }
                    }






                    when (idFromRowOfDirections) {
                        "1" -> {
                            LaunchedEffect(true) {
                                viewModel.getColumnText("ColumnOfInfo")
                            }
                            Id1Item()
                        }
                        "2" -> {
                            Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "В разработке",
                                fontSize = 25.sp)
                            }
//                            LaunchedEffect(true) {
//                                viewModel.getColumnText("Magistr")
//                            }
//                            Id2Item()
                        }
                        "3" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "В разработке",
                                    fontSize = 25.sp
                                )

                            }
                        }
                        "4" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "В разработке",
                                    fontSize = 25.sp
                                )

                            }
                        }


                    }


                }
            }


    }
}


@Composable
fun load () {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}



