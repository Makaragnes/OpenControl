package com.example.opencontrol.service

import com.example.opencontrol.model.AuthModel
import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.departments.CalendarResponce
import com.example.opencontrol.model.departments.CalendarTimesModel
import com.example.opencontrol.model.departments.DayMonthYearModel
import com.example.opencontrol.model.departments.DepID
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.model.departments.DepartmentThems
import com.example.opencontrol.model.departments.StoreResponseModel
import com.example.opencontrol.model.departments.StoreToConsultationModel
import com.example.opencontrol.model.profileModels.BusinessModel
import com.example.opencontrol.model.profileModels.BusinessModelWithoutToken
import com.example.opencontrol.model.profileModels.MainBusinessModel
import com.example.opencontrol.model.profileModels.MainPersonalInfoModel
import com.example.opencontrol.model.profileModels.PersonalInfoModel
import com.example.opencontrol.model.profileModels.PersonalInfoModelWithoutToken
import com.example.opencontrol.model.profileModels.TokenModel
import dagger.Module
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DepartmentService {

    @GET("/")
    suspend fun getHome(): Response<OneStrModel>

    @POST("/deps/")
    suspend fun getListOfDepartmens(): Response<List<DepartmentModel>>

    @POST("/chatbot/init/")
    suspend fun initChat(): Response<OneStrModel>

    @POST("/chatbot/pull/")
    suspend fun sendMessage(@Body message: OneStrModel): Response<OneStrModel>

    @POST("/deps/single/")
    suspend fun getDepartmentByID(@Body depid: DepID): Response<DepartmentThems>

    @POST("/profile/business/init/")
    suspend fun getBusinessInfo(@Body tokenModel: TokenModel): Response<BusinessModelWithoutToken>

    @POST("/profile/person/init/")
    suspend fun getPersonInfo(@Body tokenModel: TokenModel): Response<PersonalInfoModelWithoutToken>

    @POST("/profile/person/")
    suspend fun pushPersonInfo(@Body personalInfoModel: MainPersonalInfoModel): Response<MainPersonalInfoModel>

    @POST("/profile/business/")
    suspend fun pushBusinessInfo(@Body businessModel: MainBusinessModel): Response<MainBusinessModel>

    @POST("/calenda/init/")
    suspend fun sendDate(@Body dayMonthYearModel: DayMonthYearModel): Response<List<CalendarTimesModel>>

    @POST("/auth/")
    suspend fun auth(@Body authModel: AuthModel): Response<AuthModel>

    @POST("/validate/")
    suspend fun validate(@Body authModel: AuthModel): Response<AuthModel>

    @POST("/consult/storage/")
    suspend fun pushStoreConsultation(@Body storeToConsultationModel: StoreToConsultationModel): Response<StoreToConsultationModel>

    @POST("/consult/get_storage/")
    suspend fun getStore(@Body storeToConsultationModel: StoreToConsultationModel): Response<List<StoreResponseModel>>
}