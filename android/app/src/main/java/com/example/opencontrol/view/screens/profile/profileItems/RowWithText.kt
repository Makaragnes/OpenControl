package com.example.opencontrol.view.screens.profile.profileItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opencontrol.ui.theme.DarkGrey

@Composable
fun RowWithText(text: String){
    Row(
        modifier = Modifier
            .padding(8.dp, 16.dp, 8.dp, 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ){
        Text(
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
            text = text,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.W400,
            fontSize = 15.sp,
            color = DarkGrey
        )
    }
}