package com.example.opencontrol.model.departments


data class DepartmentThems(
    val num: Int,
    val link: String,
    val department: String,
    val short_name: String,
    val controll_type: List<ControlType>
)

data class ControlType(
    val type: String,
    val consult: List<Thems>
)

data class Thems(
    val theme: String
)

