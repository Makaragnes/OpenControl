package com.example.opencontrol.view.viewModel.consultation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.departments.CalendarTimesModel
import com.example.opencontrol.model.departments.StoreResponseModel
import com.example.opencontrol.model.departments.StoreToConsultationModel
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.repository.CalendarRepository
import com.example.opencontrol.repository.ConsultationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    private val consultationRepository: ConsultationRepository
): ViewModel() {

    var listOfStore: MutableState<List<StoreResponseModel>?> = mutableStateOf(null)

    suspend fun getListOfStore(){
        listOfStore.value = consultationRepository.getStore(StoreToConsultationModel(
            DataObj.auth.value!!.token,
            DataObj.auth.value!!.id,
            "",
            "",
            "",
            0,
            false
        )).body()
    }

}