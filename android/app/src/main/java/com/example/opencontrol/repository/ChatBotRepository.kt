package com.example.opencontrol.repository

import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class ChatBotRepository @Inject constructor(
    private val apiService: DepartmentService
) {
    suspend fun getInit(): Response<OneStrModel>{
        return apiService.initChat()
    }
}