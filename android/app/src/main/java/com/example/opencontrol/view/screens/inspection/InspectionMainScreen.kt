package com.example.opencontrol.view.screens.inspection

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.opencontrol.view.items.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectionMainScreen(navController: NavController){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
            )
        },
    ) {
        Text(text = "sdf")
    }
}