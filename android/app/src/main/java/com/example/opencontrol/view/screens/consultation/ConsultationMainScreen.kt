package com.example.opencontrol.view.screens.consultation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.view.items.BottomNavigationBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.viewModel.MainViewModel
import com.example.opencontrol.view.viewModel.consultation.ConsultationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationMainScreen(navController: NavController) {

    val consultationViewModel: ConsultationViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        consultationViewModel.getListOfStore()
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                        .fillMaxWidth(),
                    Arrangement.Center
                ) {
                    Text(text = "Запланированные консультации")
                }
            }

            if (consultationViewModel.listOfStore.value != null) {
            items(consultationViewModel.listOfStore.value!!.toList()) { m ->
                Column(
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
                        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                        .background(VeryLightGreen)
                        .clickable { navController.navigate(NavRoute.VeryConsultationScreen.route) }
                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
                            .fillMaxWidth(),
                        Arrangement.Start
                    ) {
                        Text(
                            text = "Консультация № ${m.cons_id}",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
                            .fillMaxWidth(),
                        Arrangement.Start
                    ) {
                        Text(
                            text = "Тема: ${m.thems}",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row(
                        modifier = Modifier
                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
                            .fillMaxWidth(),
                        Arrangement.Start
                    ) {
                        Text(
                            text = "Время: ${m.date_time}",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                    Row(
                        modifier = Modifier
                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
                            .fillMaxWidth(),
                        Arrangement.Start
                    ) {
                        Text(
                            text = "Департамент: ${m.department}",
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = DarkGrey
                        )
                    }
                }
            }

            }
//            item {
//                Column(
//                    modifier = Modifier
//                        .padding(16.dp, 16.dp, 16.dp, 16.dp)
//                        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
//                        .background(VeryLightGreen)
//                        .clickable { navController.navigate(NavRoute.VeryConsultationScreen.route) }
//                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
//                        .fillMaxWidth()
//                ) {
//                    Row(
//                        modifier = Modifier
//                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
//                            .fillMaxWidth(),
//                        Arrangement.Start
//                    ) {
//                        Text(
//                            text = "Консультация № 1Df34JU",
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.W500,
//                            fontSize = 16.sp
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
//                            .fillMaxWidth(),
//                        Arrangement.Start
//                    ) {
//                        Text(
//                            text = "Тема: ",
//                            textAlign = TextAlign.Left,
//                            fontWeight = FontWeight.W400,
//                            fontSize = 15.sp,
//                            color = DarkGrey
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
//                            .fillMaxWidth(),
//                        Arrangement.Start
//                    ) {
//                        Text(
//                            text = "Время: ",
//                            textAlign = TextAlign.Left,
//                            fontWeight = FontWeight.W400,
//                            fontSize = 15.sp,
//                            color = DarkGrey
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            //.padding(16.dp, 32.dp, 16.dp, 32.dp)
//                            .fillMaxWidth(),
//                        Arrangement.Start
//                    ) {
//                        Text(
//                            text = "Департамент: ",
//                            textAlign = TextAlign.Left,
//                            fontWeight = FontWeight.W400,
//                            fontSize = 15.sp,
//                            color = DarkGrey
//                        )
//                    }
//                }
//            }


        }
    }
}