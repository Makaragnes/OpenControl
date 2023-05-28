package com.example.opencontrol.model.departments

import com.google.gson.annotations.SerializedName


data class CalendarTimesModel(
    @SerializedName("time_val")
    val time: String,
    @SerializedName("is_busy")
    val available: Boolean
)
