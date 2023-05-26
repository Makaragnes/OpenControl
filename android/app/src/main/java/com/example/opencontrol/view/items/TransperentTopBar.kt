package com.example.opencontrol.view.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.opencontrol.R
import com.example.opencontrol.ui.theme.OpenControlTheme
import com.example.opencontrol.ui.theme.bigIndent
import com.example.opencontrol.ui.theme.transparent_color
import com.example.opencontrol.view.screens.consultation.VeryConsultationScreen

@Composable
fun TransientTopBar(navController: NavController, text: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(transparent_color),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "back",
            modifier = Modifier
                .padding(16.dp, 12.dp, 12.dp, 0.dp)
                .size(24.dp)
                .clickable { navController.navigateUp() }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 12.dp, 32.dp, 0.dp)
                .align(Alignment.CenterVertically)
            ,
            text = text,
            color = Color.Black,
            fontSize = 16.sp,
            maxLines = 3,
            textAlign = TextAlign.Center,
        )
//        if (iconRoute != "nan") {
//            Icon(
//                tint = r_blue_primary,
//                modifier = Modifier
//                    .clickable { navController.navigate(iconRoute) }
//                    .padding(likeBackArrowIndent, 0.dp, likeBackArrowIndent, 0.dp),
//                painter = painterResource(id = R.drawable.ic_baseline_qr_code_24),
//                contentDescription = "back")
//        } else { Spacer(modifier = Modifier.padding(24.dp))
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenControlTheme {
        TransientTopBar(rememberNavController(), "Департамент здравоохранения города Москвы fdgsdfgdfgdfg fgsfgsd ")
    }
}