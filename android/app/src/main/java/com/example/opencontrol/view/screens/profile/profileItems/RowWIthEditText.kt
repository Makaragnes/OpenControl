package com.example.opencontrol.view.screens.profile.profileItems

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opencontrol.ui.theme.Rose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowWithEditText(title: String, description: String, icon: ImageVector, textState: MutableState<String?>, enableField: MutableState<Boolean>){
    if (textState.value == null || textState.value == "" || textState.value == "null"){
        textState.value = ""
        Log.d("TTT", textState.value.toString())
    } else {
        textState.value
    }
    Row(
        modifier = Modifier
            .padding(8.dp, 4.dp, 8.dp, 4.dp)
            .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .background(Rose)
            .clickable { enableField.value = !enableField.value }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Text(
//            modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
//            text = title,
//            textAlign = TextAlign.Left,
//            fontWeight = FontWeight.W400,
//            fontSize = 15.sp,
//            color = Color.Black
//        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 8.dp),
            value = textState.value!!,
            enabled = enableField.value,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "emailIcon"
                )
            },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                textState.value = it
            },
            label = { Text(text = title) },
            placeholder = { Text(text = textState.value!!) },
            //"Введите ваши инициалы"
        )
    }
}