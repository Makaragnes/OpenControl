package com.example.opencontrol.view.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.opencontrol.R
import com.example.opencontrol.ui.theme.Black
import com.example.opencontrol.ui.theme.Brown
import com.example.opencontrol.ui.theme.DarkGrey
import com.example.opencontrol.ui.theme.LightGray
import com.example.opencontrol.ui.theme.MagicColor
import com.example.opencontrol.ui.theme.Rose
import com.example.opencontrol.ui.theme.VeryLightGray
import com.example.opencontrol.view.navigation.NavRoute

// "/consultation" -> {token: "", type: ""}
// /consultation/something/ -> {token: "", consid: ""}
// {
// }
sealed class BottomNavItem(
    val route: String,
    val titleResId: String,
    val icon: Int
) {
    object Home : BottomNavItem(
        route = NavRoute.MainScreen.route,
        titleResId = "Главная",
        icon = R.drawable.hometwo
    )

    object Consultation : BottomNavItem(
        route = NavRoute.ConsultationMainScreen.route,
        titleResId = "Консультант",
        icon = R.drawable.consultation
    )

    object Inspection : BottomNavItem(
        route = NavRoute.InspectionMainScreen.route,
        titleResId = "Проверки",
        icon = R.drawable.inspection
    )

    object Profile : BottomNavItem(
        route = NavRoute.ProfileMainScreen.route,
        titleResId = "Профиль",
        icon = R.drawable.persontwo
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    //lateinit var itemsd: List<BottomNavItem>;
//    val items = listOf(
//        BottomNavItem.Home,
//        BottomNavItem.Settings,
//        BottomNavItem.Permissions,
//        BottomNavItem.Profile
//    )

    val items = ArrayList<BottomNavItem>()
    items.add(BottomNavItem.Home)
    items.add(BottomNavItem.Consultation)
    items.add(BottomNavItem.Inspection)
    items.add(BottomNavItem.Profile)

    BottomNavigation(
        modifier = Modifier
            .navigationBarsPadding()
        //.padding(0.dp, 0.dp, 0.dp, 0.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .background(Rose)
                    .padding(0.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp),
                        contentDescription = "df",

                        tint = (if (currentRoute==item.route) {
                            Brown} else {
                            DarkGrey
                            }) as Color
                    )
                },
                label = {
                    Text(
                        text = item.titleResId,
                        fontSize = 12.sp,
                        color = (if (currentRoute==item.route) {
                            Black} else {
                            DarkGrey
                        }),
                        fontWeight = (if (currentRoute==item.route) {
                            FontWeight.W500
                        } else {
                            FontWeight.Normal
                        }),
                        maxLines = 1
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    Log.d("f", "s" + currentRoute.toString())
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}