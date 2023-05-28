package com.example.opencontrol.repository

import com.example.opencontrol.model.AuthModel
import com.example.opencontrol.model.departments.StoreResponseModel
import com.example.opencontrol.model.departments.StoreToConsultationModel
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class ConsultationRepository @Inject constructor(
    private val departmentService: DepartmentService
) {

    suspend fun getStore(storeToConsultationModel: StoreToConsultationModel): Response<List<StoreResponseModel>> {
        return departmentService.getStore(storeToConsultationModel)
    }
}
