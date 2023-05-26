package com.example.opencontrol.view.screens.videocall

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun VideoCallScreen(navController: NavController){
    val name = remember {
        mutableStateOf("qwerty")
    }


}