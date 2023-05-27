package com.example.opencontrol.model.departments

data class Department(
    val num: Int,
    val link: String,
    val department: String,
    val short_name: String,
    val list: List<String>
)


