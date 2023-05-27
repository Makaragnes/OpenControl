package com.example.opencontrol.view.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.model.example.DogResponse
import com.example.opencontrol.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    var response: MutableState<List<DepartmentModel>?> = mutableStateOf(null)
    var resp = mutableListOf<DepartmentModel>()


    fun getDepartments(){
        viewModelScope.launch {
            response.value = mainRepository.getDepartments().body()
            resp = mainRepository.getDepartments().body() as MutableList<DepartmentModel>
        }
    }
}