package ru.pakarp.GiefptAbitur.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.pakarp.GiefptAbitur.R
import ru.pakarp.GiefptAbitur.ui.theme.appBar
import ru.pakarp.GiefptAbitur.ui.theme.text


@Composable
fun TopAppBarFun(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()

     TopAppBar(backgroundColor = appBar) {
        Box() {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = "Menu",
                    tint = text
                    )
            }
            
        }
         
     }

}
