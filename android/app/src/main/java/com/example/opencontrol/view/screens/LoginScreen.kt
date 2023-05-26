package com.example.opencontrol.view.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.opencontrol.ui.theme.OpenControlTheme
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.R
import com.example.opencontrol.ui.theme.BeforeWhite
import com.example.opencontrol.ui.theme.Black
import com.example.opencontrol.ui.theme.LightGray
import com.example.opencontrol.ui.theme.MagicColor
import com.example.opencontrol.ui.theme.Purple80
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGray
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.VeryVeryLightGreen
import com.example.opencontrol.ui.theme.bigIndent
import com.example.opencontrol.ui.theme.normalLabelText
import com.example.opencontrol.ui.theme.standardIndent
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.viewModel.LoginScreenViewModel

import java.time.format.TextStyle
import kotlin.math.log


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val loginScreenViewModel = LoginScreenViewModel()

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = Rose.toArgb()
    window.navigationBarColor = transparent_color.toArgb()


    LaunchedEffect(Unit) {

    }

    var loginText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Rose)
                .padding(0.dp, 0.dp, 0.dp, standardIndent)
                .weight(3f),
            verticalArrangement = if (!loginScreenViewModel.availableServer.value){
                Arrangement.Center
            } else {
                Arrangement.Bottom
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "MainIcon"
                )
            }
        }
        if (loginScreenViewModel.availableServer.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .weight(5f),
                Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(0.dp, bigIndent, 0.dp, bigIndent),
                        text = "Вход", color = Black,
                        fontWeight = FontWeight.W400,
                        fontSize = normalLabelText
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    Arrangement.Center
                ) {
//                    TextField(
//                        modifier = Modifier
//                            .clip(shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)),
//                        value = loginText,
//                        onValueChange = {loginText=it})
                    OutlinedTextField(
                        value = loginText,
                        onValueChange = { loginText = it },
                        modifier = Modifier
                            .padding(64.dp, 0.dp, 64.dp, 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        maxLines = 1,
                        placeholder = { Text(text = "Введите логин")}
                        //textStyle = TextStyle(fontSize = nFontSize),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    Arrangement.Center
                ) {
//                    TextField(
//                        modifier = Modifier
//                            .clip(shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)),
//                        value = loginText,
//                        onValueChange = {loginText=it})
                    OutlinedTextField(
                        value = passwordText,
                        onValueChange = { passwordText = it },
                        modifier = Modifier
                            .padding(64.dp, 8.dp, 64.dp, 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        maxLines = 1,
                        placeholder = { Text(text = "Введите пароль")}
                        //textStyle = TextStyle(fontSize = nFontSize),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MagicColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(64.dp, 8.dp, 64.dp, 0.dp)
                            .fillMaxWidth(),
                        onClick = { navController.navigate(NavRoute.MainScreen.route) }) {
                        Text(
                            text = "Войти",
                            color = Color.White,
                            fontSize = 16.sp
                            )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    Arrangement.Center
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = VeryVeryLightGreen,
                            contentColor = DarkGray
                        ),
                        modifier = Modifier
                            .padding(64.dp, 0.dp, 64.dp, 8.dp)
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Зарегистрироваться",
                            //color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(64.dp))

            }
        }
        //Spacer(modifier = Modifier.padding(64.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenControlTheme {
        LoginScreen(rememberNavController())
    }
}