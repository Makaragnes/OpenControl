package com.example.opencontrol.view.viewModel.departmant

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.departments.CalendarTimesModel
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.model.departments.StoreToConsultationModel
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.repository.CalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
): ViewModel() {
    //var listOfTimes = mutableListOf<CalendarTimesModel>()
    //var listOfTimes = mutableStateListOf<CalendarTimesModel>()
    var listOfTimes: MutableState<List<CalendarTimesModel>?> = mutableStateOf(null)

    var listOFAvailable = mutableStateListOf<Boolean>()


    suspend fun changeDate(){
        val lst = calendarRepository.sendDate().body()
        if (lst!=null){
            listOfTimes.value = lst
        } else {
            listOfTimes.value = emptyList()
        }
    }

    suspend fun store(){
        calendarRepository.store(StoreToConsultationModel(
            DataObj.auth.value!!.token,
            DataObj.auth.value!!.id,
            DataObj.consultationTheme,
            DataObj.department,
            DataObj.data_time,
            0,
            false

        ))
    }
}