package com.example.opencontrol.view.screens.chat

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.opencontrol.R
import com.example.opencontrol.model.chatbot.MessageItem
import com.example.opencontrol.model.webrtcModels.MessageModel
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGreen
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.items.BottomNavigationBar
import com.example.opencontrol.view.items.TransientTopBar
import com.example.opencontrol.view.navigation.NavRoute
import com.example.opencontrol.view.viewModel.chatbot.ChatBotViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatMainScreen(navController: NavController){

    val chatBotViewModel: ChatBotViewModel = hiltViewModel()

    //chatBotViewModel.somelist.value.add(MessageItem("sdf", "sdf", false))

    val activity = LocalContext.current as Activity
    val window = activity.window

    window.statusBarColor = transparent_color.toArgb()
    window.navigationBarColor = transparent_color.toArgb()

    val keyboardController = current

    val message = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        chatBotViewModel.getInit()
    }

    Scaffold(
        modifier = Modifier
            .clickable { keyboardController?.hide() }
            .statusBarsPadding(),
        topBar = {
            TransientTopBar(navController = navController, "Чат-бот")
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 1,
                    placeholder = { Text(text = "Введите сообщение") },
                    trailingIcon = {
                        Box() {
                            Image(painter = painterResource(id = R.drawable.baseline_send_24),
                                contentDescription = "send image",
                                modifier = Modifier.clickable {
                                    chatBotViewModel.somelist.add(
                                        MessageItem(
                                            message = message.value,
                                            "sdf",
                                            false
                                        )
                                    )
                                    Log.d("TAG", chatBotViewModel.somelist.size.toString())
                                    message.value = ""
                                    keyboardController?.hide()
                                })
                        }
                    },
                    //textStyle = TextStyle(fontSize = nFontSize),
                )
            }
        },
        content = {
            LazyColumn(modifier = Modifier.systemBarsPadding()){
                items(chatBotViewModel.somelist.toList()) { m ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()) {
                        if (!m.whereAreYouFrom) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()) {
                            }
                        }
                        Row(
                            modifier = Modifier.weight(3f),
                            horizontalArrangement = if (m.whereAreYouFrom) {
                                Arrangement.Start
                            } else {
                                Arrangement.End
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    //.weight(3f)
                                    .clip(
                                        if (m.whereAreYouFrom) {
                                            RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
                                        } else {
                                            RoundedCornerShape(16.dp, 0.dp, 16.dp, 16.dp)
                                        }
                                    )
                                    //.fillMaxWidth(0.7f)
                                    .background(
                                        if (m.whereAreYouFrom) {
                                            Rose
                                        } else {
                                            VeryLightGreen
                                        }
                                    ),
//                            horizontalArrangement = if (m.whereAreYouFrom) {
//                                Arrangement.SpaceBetween
//                            } else {
//                                Arrangement.SpaceBetween
//                            }
                            ) {
                                Column(
                                    horizontalAlignment = if (m.whereAreYouFrom) {
                                        Alignment.Start
                                    } else {
                                        Alignment.End
                                    },
                                ) {
                                    Text(
                                        color = DarkGrey,
                                        modifier = Modifier
                                            .padding(4.dp, 4.dp, 4.dp, 0.dp),
                                        text = if (m.whereAreYouFrom) {
                                            "Чат-бот"
                                        } else {
                                            "Иванов иван иванович"
                                        },
                                        textAlign = if (m.whereAreYouFrom) {
                                            TextAlign.Left
                                        } else {
                                            TextAlign.Right
                                        },
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(4.dp, 4.dp, 4.dp, 4.dp),
                                        text = m.message,
                                        maxLines = 100,
                                        textAlign = if (m.whereAreYouFrom) {
                                            TextAlign.Left
                                        } else {
                                            TextAlign.Right
                                        }
                                    )
                                }
                            }
                        }
                        if (m.whereAreYouFrom) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()) {
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(64.dp))
                }
            }
        }

    )



}