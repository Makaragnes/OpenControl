package com.example.opencontrol.service

import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.departments.DepartmentModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface DepartmentService {

    @GET("/")
    suspend fun getHome(): Response<OneStrModel>

    @POST("/deps/")
    suspend fun getListOfDepartmens(): Response<List<DepartmentModel>>
}