package com.example.opencontrol.model.departments

data class StoreToConsultationModel(
    val token: String,
    val user_id: Int,
    val theme: String,
    val department: String,
    val date_time: String,
    val cons_id: Int,
    val status: Boolean
)

