package com.example.opencontrol.repository

import android.util.Log
import com.example.opencontrol.model.departments.CalendarResponce
import com.example.opencontrol.model.departments.CalendarTimesModel
import com.example.opencontrol.model.departments.DayMonthYearModel
import com.example.opencontrol.model.departments.StoreToConsultationModel
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class CalendarRepository @Inject constructor(
    private val apiService: DepartmentService
){

    suspend fun sendDate(): Response<List<CalendarTimesModel>> {
//        Log.d("TSD", apiService.sendDate(DayMonthYearModel(
//            //DataObj.day.value,
//            //DataObj.month.value,
//            //DataObj.year.value
//        1,
//            8,
//            2023
//        )).body().toString())
        Log.d("TSD", DataObj.day.value.toString())
        return apiService.sendDate(DayMonthYearModel(
            DataObj.day.value,
            DataObj.month.value,
            DataObj.year.value
        ))
    }
    suspend fun store(storeToConsultationModel: StoreToConsultationModel): Response<StoreToConsultationModel>{
        return apiService.pushStoreConsultation(storeToConsultationModel)
    }
}