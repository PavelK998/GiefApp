package ru.pakarp.GiefptAbitur.presentation.ui


import android.text.Html
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.pakarp.GiefptAbitur.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.pakarp.GiefptAbitur.domain.model.LazyColumnItem
import ru.pakarp.GiefptAbitur.presentation.viewModels.MainViewModel
import ru.pakarp.GiefptAbitur.presentation.navigation.Screens
import ru.pakarp.GiefptAbitur.ui.theme.*
import ru.pakarp.GiefptAbitur.utils.Constants


@Composable
fun Id1Item() {
    val viewModel = hiltViewModel<MainViewModel>()
    val lazyColumnItems = viewModel.columnText.observeAsState().value
    Log.d("testLiveData", "Id1Item: $lazyColumnItems")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 15.dp)
    ) {

        if (lazyColumnItems != null) {
            lazyColumnItems.forEach{ item ->
                ColumnItem(item = item)

        }

        }
        
    }

        
    }




@Composable
fun Id2Item() {
    val viewModel = hiltViewModel<MainViewModel>()
    val lazyColumnItems = viewModel.columnText.observeAsState().value
    Log.d("testLiveData", "Id2Item: $lazyColumnItems")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 15.dp)
    ) {

        if (lazyColumnItems != null) {
            lazyColumnItems.forEach{ item ->
                ColumnItem(item = item)

            }

        }

    }

}


@Composable
fun Id3Item() {}

@Composable
fun Id4Item() {}


@Composable
fun ColumnItem(item: LazyColumnItem) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var str: String = item.text ?: ""
    str = Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY).toString()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)

            .clickable {
                isExpanded = !isExpanded
            },
        backgroundColor = lazyItem,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "menu",
                    tint = text)

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = item.name ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = text
                    )

                }

            if (isExpanded) {
                Text(
                    modifier = Modifier.padding(7.dp),
                    text = str,
                    textAlign = TextAlign.Justify,
                    softWrap = true,
                    color = text

                )
            }


        }

    }


}

@Composable
fun MainItem() {
    Box(modifier = Modifier.fillMaxWidth() ){
        Column() {

            Text(modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
                text = Constants.ITEM1,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp)

            Text(modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
                text = Constants.ITEM2,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp)
            Text(modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = Constants.TEL,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp)

            Text(modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp),
                text = Constants.ITEM3,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp)
            Text(modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = Constants.VK,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp)

            }

        }
    }








@Composable
fun DrawerTop() {

//    var selectedImageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }

    val viewModel = hiltViewModel<MainViewModel>()

    val User = viewModel.model.observeAsState().value

    Log.d("testGetUser", "DrawerTop: $User ")

    Box( modifier = Modifier
        .fillMaxWidth()
        .height(240.dp)
        .background(Color.White),
    contentAlignment = Alignment.BottomStart,
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                modifier = Modifier.padding(bottom = 40.dp),
                painter = painterResource(id = R.drawable.gief_logo),
                contentDescription = "logo",
            )


        }
        if (User != null) {
            Text(
                modifier = Modifier.padding(top = 10.dp, start = 30.dp),
                text = "${User.name}  ${User.surName}"

                )
        }

    }
}


private lateinit var auth: FirebaseAuth
@Composable
fun DrawerBottom(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    auth = Firebase.auth
    LaunchedEffect(true ){
        viewModel.getUserInfo(auth.currentUser!!.uid)

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(drawer),
        contentAlignment = Alignment.BottomCenter,
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Button(
                onClick = {
                    auth.signOut()
                    if (auth.currentUser == null) {
                        navController.navigate(Screens.AuthScreen.route)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(20.dp)
            ) {
                Text(text = "Выйти из аккаунта")
            }
        }
    }
}

    
    
    


