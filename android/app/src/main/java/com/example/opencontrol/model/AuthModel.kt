package com.example.opencontrol.model

import com.example.opencontrol.model.profileModels.TokenVal

data class AuthModel(
    val email: String,
    val token: String,
    val id: Int
)
