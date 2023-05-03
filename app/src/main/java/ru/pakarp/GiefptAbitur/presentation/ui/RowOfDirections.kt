package ru.pakarp.GiefptAbitur.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.pakarp.GiefptAbitur.domain.model.RowOfDirections
import ru.pakarp.GiefptAbitur.presentation.viewModels.MainViewModel
import ru.pakarp.GiefptAbitur.ui.theme.appBar
import ru.pakarp.GiefptAbitur.ui.theme.rowItem
import ru.pakarp.GiefptAbitur.ui.theme.text


@SuppressLint("UnrememberedMutableState")
@Composable
fun RowOfDirectionsItem(rowOfDirections: RowOfDirections) {
//    var rotationBool = true
//    var rotation = mutableStateOf(0f)


    val viewModel = hiltViewModel<MainViewModel>()
//    var isExpanded by remember {
//        mutableStateOf(false)
//    }











    Card(
        modifier = Modifier
            .clickable {

                viewModel.stateOfRODItem.value = rowOfDirections.id?: ""


//                if (isExpanded){
//                    viewModel.stateOfRODItem.value = "null"
//                    isExpanded = false
//                } else{
//                viewModel.stateOfRODItem.value = rowOfDirections.id ?:""
//                isExpanded = true}


            }
            ,
        backgroundColor = if (viewModel.stateOfRODItem.value == rowOfDirections.id) {
            appBar} else{
            rowItem},
        elevation = 8.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                modifier = Modifier.padding(15.dp),
                text = rowOfDirections.name ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (viewModel.stateOfRODItem.value == rowOfDirections.id) {
                    text} else{
                    Color.Black
                }
            )

        }


    }

}
