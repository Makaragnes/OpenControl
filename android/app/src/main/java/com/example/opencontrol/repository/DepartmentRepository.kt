package com.example.opencontrol.repository

import com.example.opencontrol.model.departments.DepID
import com.example.opencontrol.model.departments.DepartmentThems
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class DepartmentRepository @Inject constructor(
    private val apiService: DepartmentService
){

    suspend fun getDepartmentByID(): Response<DepartmentThems> {
        return apiService.getDepartmentByID(DepID(DataObj.departmentNumber.value))
    }
}