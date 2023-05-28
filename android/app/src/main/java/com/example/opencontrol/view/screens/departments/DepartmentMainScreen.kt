package com.example.opencontrol.view.screens.departments

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.opencontrol.constants.Constants
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.items.TransientTopBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.screens.LoginScreen
import com.example.opencontrol.view.viewModel.departmant.DepartmentViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentMainScreen(navController: NavController){

    val departmentViewModel: DepartmentViewModel = hiltViewModel()

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = transparent_color.toArgb()
    window.navigationBarColor = transparent_color.toArgb()

    var listOFAvailable = remember {
        mutableListOf<Boolean>()
    }

    LaunchedEffect(Unit){
        departmentViewModel.getDepartmentByID()
    }

    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
        ,
        topBar = {
            Box {
                TransientTopBar(
                    navController = navController,
                    departmentViewModel.depState.value.short_name
                )
            }
        },
        content = {
            if (
                departmentViewModel.depState.value.num != 0
            ) {
                LazyColumn(
                    modifier = Modifier
                        .statusBarsPadding()
                    //.padding(48.dp)
                    //.fillMaxSize()
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .padding(16.dp, 16.dp, 16.dp, 16.dp)
                                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                                .background(VeryLightGreen)
                                .padding(0.dp, 0.dp, 8.dp, 0.dp)
                                .fillMaxWidth(),
                            Arrangement.Center
                        ) {
                            AsyncImage(
                                //painter = painterResource(id = R.drawable.tradeservice),
                                model = "${Constants.BASE_URL}/${departmentViewModel.depState.value.link.drop(2)}",
                                contentDescription = "in",
                                modifier = Modifier
                                    .size(128.dp)
                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                                    .fillMaxSize()
                                    .background(transparent_color)
                                //.fillMaxWidth()
                            )
                            Text(
                                textAlign = TextAlign.Left,
                                modifier = Modifier.align(CenterVertically),
                                text = departmentViewModel.depState.value.department)
                        }
                    }

                    for (item in departmentViewModel.depState.value.controll_type) {
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                    .fillMaxWidth(),
                                Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(32.dp, 16.dp, 16.dp, 0.dp),
                                    text = "Орган надзора и контроля"
                                )
                            }
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp, 16.dp, 16.dp, 16.dp)
                                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                                    .background(VeryLightGreen)
                                    //.clickable { navController.navigate(NavRoute.VeryConsultationScreen.route) }
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
                                        text = "    " + item.type,
                                        textAlign = TextAlign.Justify,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                    .fillMaxWidth(),
                                Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(32.dp, 16.dp, 16.dp, 0.dp),
                                    text = "Темы консультаций"
                                )
                            }
                        }
                        items(item.consult) { m ->
                            Column(
                                modifier = Modifier
                                    .padding(48.dp, 8.dp, 16.dp, 8.dp)
                                    .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                                    .background(VeryLightGreen)
                                    .clickable {
                                        DataObj.consultationTheme = m.theme
                                        Log.d("THEME", DataObj.consultationTheme)
                                        DataObj.department = item.type
                                        navController.navigate(NavRoute.CalendarScreen.route) }
                                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(0.dp, 8.dp, 0.dp, 0.dp)
                                        .fillMaxWidth(),
                                    Arrangement.Start
                                ) {
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
                                        text = m.theme,
                                        textAlign = TextAlign.Left,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 15.sp,
                                        color = DarkGrey
                                    )
                                }
                            }
                        }
                    }
                }
            }
            else {

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DepartmentPreview() {
    OpenControlTheme {
        DepartmentMainScreen(rememberNavController())
    }
}