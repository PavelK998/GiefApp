package ru.pakarp.GiefptAbitur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import ru.pakarp.GiefptAbitur.presentation.navigation.SetupNavHost
import ru.pakarp.GiefptAbitur.ui.theme.GiefptAbiturTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)

        setContent {
            val navController = rememberNavController()
            GiefptAbiturTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupNavHost(navController = navController)
                }
            }
        }
    }
}

