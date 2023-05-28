package com.example.opencontrol.view.screens.departments

import android.annotation.SuppressLint
import android.app.Activity
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.opencontrol.model.departments.IndexBoolModel
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.repository.CalendarRepository
import com.example.opencontrol.ui.theme.LightGray
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.items.TransientTopBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.viewModel.departmant.CalendarViewModel
import com.example.opencontrol.view.viewModel.departmant.DepartmentViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CalendarScreen(navController: NavController) {

    val calendarViewModel: CalendarViewModel = hiltViewModel()

    val coroutineScope = rememberCoroutineScope()

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = transparent_color.toArgb()
    window.navigationBarColor = transparent_color.toArgb()

    LaunchedEffect(Unit) {
        calendarViewModel.changeDate()
        //departmentViewModel.getDepartmentByID()
    }

    val indexes = remember {
        mutableStateListOf<IndexBoolModel>()
    }

    val check = remember {
        mutableStateOf(false)
    }

    var mainIndex = 0;

    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            Box {
                TransientTopBar(
                    navController = navController,
                    text = "Выберите дату консультации"
                )
            }
        },
        content = {
            LazyColumn() {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {

                        AndroidView(
                            { CalendarView(it) },
                            modifier = Modifier.wrapContentWidth(),
                            update = { views ->
                                views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
//                                    DataObj.dayMonthYear.value.day = dayOfMonth
//                                    DataObj.dayMonthYear.value.month = month
//                                    DataObj.dayMonthYear.value.year = year
                                    DataObj.day.value = dayOfMonth
                                    DataObj.month.value = month
                                    DataObj.year.value = year
                                    coroutineScope.launch {
                                        calendarViewModel.changeDate()
                                    }
                                    Log.d("QQQ", dayOfMonth.toString())
                                }
                            }
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    )
                    {
                        Text(
                            text = "Выберите время консультации",
                            textAlign = TextAlign.Center
                        )

                    }
                }
                if (calendarViewModel.listOfTimes.value != null) {
                    Log.d("LST", "IDNSAFOIDNFI")
                    if (calendarViewModel.listOfTimes.value!!.toList().isEmpty()){
                        item {
                            Spacer(modifier = Modifier.padding(64.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(6.dp),
                                Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Text(
                                    text = "Нет доступных консультаций",
                                    textAlign = TextAlign.Center
                                )

                            }
                        }
                    }
                    itemsIndexed(calendarViewModel.listOfTimes.value!!.toList()) { index, i ->
                        Log.d("ASDF", i.time + i.available)
                        if (i.available) {
                            indexes.add(IndexBoolModel(index, false))
                            calendarViewModel.listOFAvailable.add(false)
                            Row(
                                modifier = Modifier
                                    .padding(16.dp, 4.dp, 16.dp, 4.dp)
                                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                                    .background(
                                        if (!indexes[index].selected && check.value) {
                                            Color.White
                                        } else {
                                            VeryLightGreen
                                        }
                                    )
                                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .weight(1f),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp, 8.dp, 8.dp, 8.dp),
                                        text = "Время:",
                                        color = if (!indexes[index].selected && check.value) {
                                            LightGray
                                        } else {
                                            Color.Black
                                        },
                                        textAlign = TextAlign.Center,
                                        fontWeight = if (!indexes[index].selected && check.value) {
                                            FontWeight.Normal
                                        } else {
                                            FontWeight.W500
                                        },
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp, 8.dp, 8.dp, 8.dp),
                                        text = "   ${i.time}",
                                        color = if (!indexes[index].selected && check.value) {
                                            LightGray
                                        } else {
                                            Color.Black
                                        },
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = indexes[index].selected,
                                        onCheckedChange = {
                                            if (!check.value) {
                                                indexes[index].selected = !indexes[index].selected
                                                check.value = true
                                                mainIndex = index
                                                DataObj.data_time = i.time
                                            } else if (indexes[index].selected) {
                                                indexes[index].selected = !indexes[index].selected
                                                check.value = false
                                                DataObj.data_time = ""
                                            }

                                        }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(6.dp),
                            Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                text = "Выберите время консультации",
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }
                item {
                    if (check.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        calendarViewModel.store()
                                        navController.navigate(NavRoute.MainScreen.route)
                                        Toast.makeText(activity, "Вы записались на консультацию", Toast.LENGTH_LONG).show()
                                    }
                                    // HERE mainIndex ot backend
                                }) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    androidx.compose.material3.Text(text = "Записаться на консультацию")
                                }
                            }
                        }
                    }
                }
            }

        }
    )
}