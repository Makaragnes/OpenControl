package com.example.opencontrol.view.viewModel.departmant

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.opencontrol.model.departments.DepartmentThems
import com.example.opencontrol.repository.DepartmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository
): ViewModel() {

    var depState = mutableStateOf<DepartmentThems>(
        DepartmentThems(
            0,
            "",
            "",
            "",
            listOf()
        )
    )

    suspend fun getDepartmentByID(){
        depState.value = departmentRepository.getDepartmentByID().body()!!
    }
}