package ru.pakarp.GiefptAbitur.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.collections.immutable.toImmutableList
import ru.pakarp.GiefptAbitur.R
import ru.pakarp.GiefptAbitur.presentation.screens.AspirantScreen
import ru.pakarp.GiefptAbitur.presentation.screens.SplashScreen
import ru.pakarp.GiefptAbitur.presentation.screens.SpoScreen
import ru.pakarp.GiefptAbitur.presentation.screens.StudentScreen
import ru.pakarp.GiefptAbitur.presentation.screens.authScreen.AuthScreen
import ru.pakarp.GiefptAbitur.presentation.screens.mainScreen.MainScreen
import ru.pakarp.GiefptAbitur.presentation.screens.registerScreen.RegisterScreen
import ru.pakarp.GiefptAbitur.ui.theme.appBar


sealed class Screens (val route: String) {
    object AuthScreen : Screens(route = "auth_screen")

    object MainScreen : Screens(route = "main_screen")
    object StudentScreen : Screens(route = "student_screen")
    object AspirantScreen : Screens(route = "aspirant_screen")
    object SpoScreen : Screens(route = "spo_screen")
    object SplashScreen: Screens(route = "splash_screen")
    object RegisterScreen : Screens(route = "register_screen")
}

sealed class BottomItem (val title: String, val iconId: Int, val route: String){
    object Abitur: BottomItem("Абитуриенту", R.drawable.abiturient, "main_screen")
    object Student: BottomItem("Студенту", R.drawable.student, "student_screen")
    object Aspirant: BottomItem("Аспиранту", R.drawable.aspirant, "aspirant_screen")
   // object Spo: BottomItem("СПО", R.drawable.spo, "spo_screen")

}


@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screens.SplashScreen.route ){

        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)
        }

        composable(route = Screens.AuthScreen.route){
        AuthScreen(navController = navController)
        }
        composable(route = Screens.MainScreen.route){
        MainScreen(navController = navController)
        }
        composable(route = Screens.RegisterScreen.route){
        RegisterScreen(navController = navController)
        }

        composable(route = Screens.StudentScreen.route){
           StudentScreen(navController = navController)
        }

        composable(route = Screens.AspirantScreen.route){
            AspirantScreen(navController = navController)
        }

        composable(route = Screens.SpoScreen.route){
            SpoScreen(navController = navController)
        }

    }
}


@Composable
fun BottomNav(navController: NavController) {

    val listItems = listOf(
        BottomItem.Abitur,
        BottomItem.Student,
        BottomItem.Aspirant,
      //  BottomItem.Spo
    ).toImmutableList()



    BottomNavigation( backgroundColor = appBar) {

        val backStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = backStackEntry?.destination?.route




        listItems.forEach{ item ->

            BottomNavigationItem(selected = currentRoute == item.route,

                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route) {
                                saveState = true
                            }

                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },

                icon = {

                    Icon(painter = painterResource(id = item.iconId), contentDescription ="" )
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White


            )
        }

    }
    
}