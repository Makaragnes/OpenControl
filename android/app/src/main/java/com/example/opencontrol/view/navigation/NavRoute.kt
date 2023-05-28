package com.example.opencontrol.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.view.screens.LoginScreen
import com.example.opencontrol.view.screens.MainScreen
import com.example.opencontrol.view.screens.chat.ChatMainScreen
import com.example.opencontrol.view.screens.consultation.ConsultationMainScreen
import com.example.opencontrol.view.screens.consultation.VeryConsultationScreen
import com.example.opencontrol.view.screens.consultation.WebRtcScreen
import com.example.opencontrol.view.screens.departments.CalendarScreen
import com.example.opencontrol.view.screens.departments.DepartmentMainScreen
import com.example.opencontrol.view.screens.inspection.InspectionMainScreen
import com.example.opencontrol.view.screens.profile.ProfileMainScreen
import com.example.opencontrol.view.screens.videocall.VideoCallScreen

sealed class NavRoute(val route: String) {
    object LoginScreen: NavRoute("LoginScreen")
    object MainScreen: NavRoute("MainScreen")
    
    // consultation screens
    object ConsultationMainScreen: NavRoute("ConsultationMainScreen")
    object VeryConsultationScreen: NavRoute("VeryConsultationScreen")

    // Inspection screens
    object InspectionMainScreen: NavRoute("InspectionMainScreen")
    
    // Profile screens
    object ProfileMainScreen: NavRoute("ProfileMainScreen")

    // Department screens
    object DepartmentMainScreen: NavRoute("DepartmentMainScreen")
    object CalendarScreen: NavRoute("CalendarScreen")

    // Video call screens
    object VideoCallScreen: NavRoute("VideoCallScreen.kt")

    // Chat screens
    object ChatMainScreen: NavRoute("ChatMainScreen")

    object WebRtcScreen: NavRoute("WebRtcScreen")
}

@Composable
fun OpenControlNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.LoginScreen.route) {
        composable(NavRoute.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(NavRoute.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(NavRoute.ConsultationMainScreen.route) {
            ConsultationMainScreen(navController = navController)
        }
        composable(NavRoute.InspectionMainScreen.route) {
            InspectionMainScreen(navController = navController)
        }
        composable(NavRoute.ProfileMainScreen.route) {
            ProfileMainScreen(navController = navController)
        }
        composable(NavRoute.VeryConsultationScreen.route) {
            VeryConsultationScreen(navController = navController)
        }
        composable(NavRoute.DepartmentMainScreen.route) {
            DepartmentMainScreen(navController = navController)
        }
        composable(NavRoute.VideoCallScreen.route) {
            VideoCallScreen(navController = navController)
        }
        composable(NavRoute.ChatMainScreen.route) {
            ChatMainScreen(navController = navController)
        }
        composable(NavRoute.CalendarScreen.route) {
            CalendarScreen(navController = navController)
        }
        composable(NavRoute.WebRtcScreen.route) {
            WebRtcScreen(navController = navController)
        }

    }
}