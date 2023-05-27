package com.example.opencontrol.view.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.opencontrol.R
import com.example.opencontrol.constants.Constants.Companion.BASE_URL
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.High_Priority
import com.example.opencontrol.ui.theme.MagicColor
import com.example.opencontrol.ui.theme.MainLight
import com.example.opencontrol.ui.theme.MainRed
import com.example.opencontrol.ui.theme.Main_30
import com.example.opencontrol.ui.theme.Main_70
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.achromatic_100
import com.example.opencontrol.ui.theme.achromatic_20
import com.example.opencontrol.ui.theme.achromatic_40
import com.example.opencontrol.ui.theme.achromatic_50
import com.example.opencontrol.ui.theme.achromatic_90
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.items.BottomNavigationBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.viewModel.LoginScreenViewModel
import com.example.opencontrol.view.viewModel.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){

    val mainViewModel: MainViewModel = hiltViewModel()

    val list by mainViewModel.response

    var state = remember {
        mutableListOf<DepartmentModel>()
    }

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = Rose.toArgb()
    window.navigationBarColor = Rose.toArgb()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        mainViewModel.getDepartments()
    }



    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        },

        floatingActionButton = {
            if (currentRoute == "MainScreen") {
                Button(
                    onClick = { navController.navigate(NavRoute.ChatMainScreen.route) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_message_24),
                            contentDescription = "message")
                        Text(text = "Чат бот")
                    }
                }
            }
        },
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {

                Row(
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                        .background(achromatic_100)
                        .padding(16.dp, 32.dp, 16.dp, 32.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    Text(text = "Нет новых уведомлений")
                }



                Row(
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    Text(
                        text = "Департаменты города Москвы",
                        fontWeight = FontWeight.W500
                        )
                }

                LazyRow(
                    modifier = Modifier
                        .padding(16.dp, 8.dp, 16.dp, 16.dp)
                        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                ) {
                    items(mainViewModel.resp) { m ->
                        Log.d("GGG", "$BASE_URL/$m.link")
                        Surface(
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                                .clickable { navController.navigate(NavRoute.DepartmentMainScreen.route) }
                                .height(250.dp)
                                .width(152.dp)
                                .border(
                                    BorderStroke(2.dp, MainRed),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(Color.White),

                            //horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize())
                            {
                                val height = 250.dp.toPx()
                                val width = 152.dp.toPx()

                                val trianglePath = Path().apply {
                                    // Moves to top center position
                                    moveTo(width, 0f-10f)
                                    // Add line to right corner above circle
                                    lineTo(x = width, y = height)
                                    //Add line to left corner above circle
                                    lineTo(x = 0f, y = height)
                                }

//                                val trianglePathTwo = Path().apply {
//                                    // Moves to top center position
//                                    moveTo(0f, width)
//                                    // Add line to right corner above circle
//                                    lineTo(x = width, y = height)
//                                    //Add line to left corner above circle
//                                    lineTo(x = 0f, y = height)
//                                }

                                drawPath(
                                    color = VeryLightGreen,
                                    path = trianglePath
                                )
//                                drawPath(
//                                    color = VeryLightGreen,
//                                    path = trianglePathTwo
//                                )


//                                val smilePadding = size.width * 0.25f
//                                drawArc(
//                                    color = VeryLightGreen,
//                                    startAngle = 75f,
//                                    sweepAngle = 240f,
//                                    useCenter = true,
//                                    topLeft = Offset(smilePadding, smilePadding),
//                                    size = Size(size.width - (smilePadding * 2f), size.height - (smilePadding * 2f))
//                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                AsyncImage(
                                    //painter = painterResource(id = R.drawable.tradeservice),
                                    model = "$BASE_URL/${m.link}",
                                    contentDescription = "in",
                                    modifier = Modifier
                                        .size(128.dp)
                                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
                                        .fillMaxSize()
                                        .background(transparent_color)
                                    //.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier
                                        //.padding(4.dp, 12.dp, 4.dp, 4.dp)
                                        .fillMaxSize()

                                        .background(transparent_color),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            //.background(transparent_color)
                                            .padding(4.dp, 8.dp, 4.dp, 4.dp),
                                        text = m.short_name,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }

//                    item {
//                        Column(
//                            modifier = Modifier
//                                .padding(4.dp, 0.dp, 4.dp, 0.dp)
//                                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
//                                .height(250.dp)
//                                .width(152.dp)
//                                .background(VeryLightGreen),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.housing_transformed),
//                                contentDescription = "in",
//                                modifier = Modifier
//                                    .size(128.dp)
//                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
//                                    .fillMaxWidth(),
//
//                            )
//                            Text(
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
//                                text = "Государственная инспекция по контролю за использованием объектов недвижимости",
//                                color = Color.Black,
//                                fontWeight = FontWeight.W400,
//                                fontSize = 14.sp
//
//                            )
//                        }
//                    }

//                    item {
//                        Column(
//                            modifier = Modifier
//                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
//                                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
//                                .height(242.dp)
//                                .width(160.dp)
//                                .background(achromatic_100)
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.hels),
//                                contentDescription = "in",
//                                modifier = Modifier
//                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
//                                    .fillMaxWidth()
//                            )
//                            Text(
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
//                                text = "Департамент здравоохранения"
//                            )
//                        }
//                    }
                }

            Row(
                modifier = Modifier
                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                    .background(achromatic_50)
                    .padding(16.dp, 32.dp, 16.dp, 32.dp)

                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            //textAlign = Alignment.Center,
                            text = "Ваш профиль не заполнен",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            color = High_Priority
                        )
                        Icon(
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 16.dp, 0.dp),
                            painter = painterResource(id = R.drawable.baseline_priority_high_24),
                            tint = MainRed,
                            contentDescription = "message")
                    }

                    Text(
                        text = "Пожалуйста кликините, чтобы заполнить",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                    .background(VeryLightGreen)
                    .padding(16.dp, 32.dp, 16.dp, 32.dp)
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(text = "Штрафов нет!")
            }
            
            Spacer(modifier = Modifier.padding(64.dp))
        }
        

        //Text(text = "Департаменты")
    }
}


fun emptyComponent(){

}