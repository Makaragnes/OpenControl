package com.example.opencontrol.view.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.R
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.view.items.BottomNavigationBar
import com.example.opencontrol.view.screens.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileMainScreen(navController: NavController) {

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = Rose.toArgb()
    window.navigationBarColor = Rose.toArgb()

    var text by rememberSaveable { mutableStateOf("") }

    //val enabledFields by rememberSaveable { mutableStateOf(false) }
    val enabledFields = remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .background(Rose)
                    .padding(0.dp, 32.dp, 0.dp, 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "persone photo",
                    modifier = Modifier
                        .size(128.dp),

                )
            }

            Row(
                modifier = Modifier
                    .padding(8.dp, 16.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ){
                Text(
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                    text = "Личная информация",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = DarkGrey
                )
            }

            Row(
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                    .background(Rose)
                    .clickable { enabledFields.value = !enabledFields.value }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
                    text = "ФИО: ",
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 16.dp, 8.dp),
                    value = text,
                    enabled = enabledFields.value,
                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "emailIcon") },
                    //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                    onValueChange = {
                        text = it
                    },
                    label = { Text(text = "ФИО") },
                    placeholder = { Text(text = "Введите ваши инициалы") },
                )
//                TextField(
//                    value = text,
//                    onValueChange = { text = it },
//                    enabled = true,
//                    label = { Text(
//                        modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp),
//                        text = "Молодцов Владислав Сергеевич",
//                        textAlign = TextAlign.Left,
//                        fontWeight = FontWeight.W400,
//                        fontSize = 15.sp,
//                        color = DarkGrey
//                    ) },
//                    singleLine = true
//                )
//                Text(
//                    text = "Молодцов Владислав Сергеевич ",
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.W400,
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons(enabledFields: MutableState<Boolean>) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    OpenControlTheme {
        ProfileMainScreen(rememberNavController())
    }
}