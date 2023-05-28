package com.example.opencontrol.view.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.R
import com.example.opencontrol.model.profileModels.PersonalInfoModel
import com.example.opencontrol.model.uiModels.RowProfileModel
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.view.items.BottomNavigationBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.screens.LoginScreen
import com.example.opencontrol.view.screens.profile.profileItems.RowWithEditText
import com.example.opencontrol.view.screens.profile.profileItems.RowWithText
import com.example.opencontrol.view.viewModel.MainViewModel
import com.example.opencontrol.view.viewModel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileMainScreen(navController: NavController) {

    val profileViewModel: ProfileViewModel = hiltViewModel()

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = Rose.toArgb()
    window.navigationBarColor = Rose.toArgb()

    val coroutineScope = rememberCoroutineScope()

    val text = remember { mutableStateOf("") }

//    val listOFFields = remember {
//        mutableListOf<RowProfileModel>()
//    }

    LaunchedEffect(UInt){
        profileViewModel.getBusinessInfo()
        profileViewModel.getPersonInfo()
    }


    //val enabledFields by rememberSaveable { mutableStateOf(false) }
    val enabledField = remember {
        mutableStateOf(false)
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
            }

            item {
                RowWithText("Личная информация")
            }

            item {
                RowWithEditText(
                    title = "ФИО",
                    description = "Введите ваши инициалы",
                    icon = Icons.Default.Person,
                    textState = profileViewModel.full_name,
                    enableField = enabledField
                )
                RowWithEditText(
                    title = "телефон",
                    description = "Введите ваш телефон",
                    icon = Icons.Default.Phone,
                    textState = profileViewModel.phone,
                    enableField = enabledField
                )
                RowWithEditText(
                    title = "email",
                    description = "Введите ваш email",
                    icon = Icons.Default.Email,
                    textState = profileViewModel.email,
                    enableField = enabledField
                )

                RowWithText("Информация о бизнесе")

                RowWithEditText(
                    title = "Введите ваш ОГРН",
                    description = "Введите ваш ОГРН",
                    icon = Icons.Default.List,
                    textState = profileViewModel.ogrn,
                    enableField = enabledField
                )
                RowWithEditText(
                    title = "Введите ваш ИНН",
                    description = "Введите ваш ИНН",
                    icon = Icons.Default.List,
                    textState = profileViewModel.inn,
                    enableField = enabledField
                )
                RowWithEditText(
                    title = "Введите ваше название",
                    description = "Введите ваше сокрашенное название",
                    icon = Icons.Default.List,
                    textState = profileViewModel.shortTitle,
                    enableField = enabledField
                )
//                RowWithEditText(
//                    title = "Введите ваш email",
//                    description = "На",
//                    icon = Icons.Default.List,
//                    textState = profileViewModel.nameOfTacService,
//                    enableField = enabledField
//                )
                RowWithEditText(
                    title = "Введите ваш уставной капитал",
                    description = "Введите ваш уставной капитал",
                    icon = Icons.Default.List,
                    textState = profileViewModel.establishedCapital,
                    enableField = enabledField
                )
                RowWithEditText(
                    title ="Введите род дейтельности вашего бизнеса",
                    description = "Введите род дейтельности вашего бизнеса",
                    icon = Icons.Default.List,
                    textState = profileViewModel.infoAboutActivity,
                    enableField = enabledField
                )
                RowWithEditText(
                    title = "Введите дополнительные услуги",
                    description = "Введите дополнительные услуги",
                    icon = Icons.Default.List,
                    textState = profileViewModel.additionalActivity,
                    enableField = enabledField
                )
                if (enabledField.value) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                enabledField.value = false
                                coroutineScope.launch {
                                    profileViewModel.pushPersonInfo()
                                    profileViewModel.pushBusinessInfo()
                                }
                            }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Сохранить изменения")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(64.dp))
//                RowWithEditText(
//                    title = "телефон",
//                    description = "Введите ваш телефон",
//                    icon = 1,
//                    textState = ,
//                    enableField = enabledField
//                )
//                RowWithEditText(
//                    title = "телефон",
//                    description = "Введите ваш телефон",
//                    icon = 1,
//                    textState = profileViewModel.phone,
//                    enableField = enabledField
//                )
//                RowWithEditText(
//                    title = "телефон",
//                    description = "Введите ваш телефон",
//                    icon = 1,
//                    textState = profileViewModel.phone,
//                    enableField = enabledField
//                )
            }


//            Row(
//                modifier = Modifier
//                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
//                    .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
//                    .background(Rose)
//                    .clickable { enabledField.value = !enabledField.value }
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Text(
//                    modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
//                    text = "ФИО: ",
//                    textAlign = TextAlign.Left,
//                    fontWeight = FontWeight.W400,
//                    fontSize = 15.sp,
//                    color = Color.Black
//                )
//                OutlinedTextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(0.dp, 0.dp, 16.dp, 8.dp),
//                    value = text,
//                    enabled = enabledField.value,
//                    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "emailIcon") },
//                    //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
//                    onValueChange = {
//                        text = it
//                    },
//                    label = { Text(text = "ФИО") },
//                    placeholder = { Text(text = "Введите ваши инициалы") },
//                )
//            }
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