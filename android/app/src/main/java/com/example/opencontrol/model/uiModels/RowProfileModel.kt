package com.example.opencontrol.model.uiModels

import androidx.compose.runtime.MutableState

data class RowProfileModel(
    val title: String,
    val description: String,
    val icon: Int,
    val textState: String,
    val enableField: MutableState<Boolean>
)
