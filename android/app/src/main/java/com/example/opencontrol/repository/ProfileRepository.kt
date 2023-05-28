package com.example.opencontrol.repository

import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.model.profileModels.BusinessModel
import com.example.opencontrol.model.profileModels.BusinessModelWithoutToken
import com.example.opencontrol.model.profileModels.MainBusinessModel
import com.example.opencontrol.model.profileModels.MainPersonalInfoModel
import com.example.opencontrol.model.profileModels.PersonalInfoModel
import com.example.opencontrol.model.profileModels.PersonalInfoModelWithoutToken
import com.example.opencontrol.model.profileModels.TokenModel
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class ProfileRepository @Inject constructor(
    private val apiService: DepartmentService

) {

    suspend fun getBusinessInfo(): Response<BusinessModelWithoutToken> {
        return apiService.getBusinessInfo(TokenModel(DataObj.authToken))
    }

    suspend fun getPersonInfo(): Response<PersonalInfoModelWithoutToken> {
        return apiService.getPersonInfo(TokenModel(DataObj.authToken))
    }

    suspend fun pushPersonInfo(personalInfoModel: MainPersonalInfoModel): Response<MainPersonalInfoModel> {
        return apiService.pushPersonInfo(personalInfoModel)
    }

    suspend fun pushBusinessInfo(businessModel: MainBusinessModel): Response<MainBusinessModel> {
        return apiService.pushBusinessInfo(businessModel)
    }
}