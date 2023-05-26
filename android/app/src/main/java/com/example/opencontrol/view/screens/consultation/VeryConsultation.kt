package com.example.opencontrol.view.screens.consultation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.CallActivity
import com.example.opencontrol.R
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.MainGreen
import com.example.opencontrol.ui.theme.MainYellow
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.items.TransientTopBar
import com.example.opencontrol.view.screens.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VeryConsultationScreen(navController: NavController){

    val activity = LocalContext.current as Activity
    val window = activity.window

    val context = LocalContext.current

    window.statusBarColor = transparent_color.toArgb()
    window.navigationBarColor = transparent_color.toArgb()

    Scaffold(
        topBar = {
            TransientTopBar(navController = navController, "Консультация № 1Df34JU")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
//            Row(
//                modifier = Modifier
//                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
//                    .fillMaxWidth(),
//                Arrangement.Center
//            ) {
////                Text(
////                    text = "Консультация № 1Df34JU",
////                    textAlign = TextAlign.Center,
////                    fontWeight = FontWeight.W500,
////                    fontSize = 16.sp
////                )
//            }

            Row(
                modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 8.dp)
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(
                    text = "(текущая) ",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = DarkGrey
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 16.dp)
                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                    .background(VeryLightGreen)
                    .padding(16.dp, 32.dp, 16.dp, 32.dp)
                    .fillMaxWidth(),
                Arrangement.Center
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.hels),
                        contentDescription = "in",
                        modifier = Modifier
                            .padding(8.dp, 8.dp, 8.dp, 8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
                        text = "Департамент здравоохранения города Москвы",
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row() {
                        Text(
                            modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                            text = "Время консультации: ",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                            text = "12:00-13:00",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row() {
                        Text(
                            modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                            text = "Тема: ",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                            text = "Региональный государственный контроль (надзор) за применением цен на лекарственные препараты, включенные в перечень жизненно необходимых и важнейших лекарственных препаратов",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row() {
                        Text(
                            modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                            text = "Инспектор: ",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                            text = "Будунов Владислав Иванович",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row() {
                        Text(
                            modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                            text = "Предприниматель: ",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                            text = "Владов Игнат Валерьевич",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row() {
                        Text(
                            modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                            text = "Формат: ",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
                            text = "Переписка/Видеоконференция",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainGreen
                            ),
                            onClick = {
                                val intent = Intent(context, CallActivity::class.java)
                                intent.putExtra("username", "asdf")
                                context.startActivity(intent)
                            }) {
                            Text(
                                //modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                                text = "Перейти к созвону",
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainYellow
                            ),
                            onClick = { /*TODO*/ }) {
                            Text(
                                //modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp),
                                text = "Завершить консультацию ",
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }
                    }


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenControlTheme {
        VeryConsultationScreen(rememberNavController())
    }
}