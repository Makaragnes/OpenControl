package com.example.opencontrol.obj

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.opencontrol.model.AuthModel
import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.departments.DayMonthYearModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

object DataObj {
    val departmentNumber = mutableStateOf<Int>(0)

    val c = Calendar.getInstance()

    val day = mutableStateOf(c.get(Calendar.DAY_OF_MONTH))
    val month = mutableStateOf(c.get(Calendar.MONTH))
    val year = mutableStateOf(c.get(Calendar.YEAR))

//    val day = mutableStateOf(1)
//    val month = mutableStateOf(8)
//    val year = mutableStateOf(2023)


//    val dayMonthYear = mutableStateOf(
//        DayMonthYearModel(
//            c.get(Calendar.DAY_OF_MONTH),
//            c.get(Calendar.MONTH),
//            c.get(Calendar.YEAR)))

    val authToken = "sdfnsdfsddsdfvavfdvsdjfs"

    var auth: MutableState<AuthModel?> = mutableStateOf(null)

    var consultationTheme = ""
    var department = ""
    var data_time = ""

}