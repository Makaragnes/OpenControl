package com.example.opencontrol.view.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opencontrol.model.example.DogResponse
import com.example.opencontrol.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    var response: MutableState<DogResponse?> = mutableStateOf(null)

    private var _movieResponse = MutableLiveData<Response<DogResponse>>()
    val movieResponse: LiveData<Response<DogResponse>> = _movieResponse

    val availableServer = mutableStateOf(true)

    fun getData() {
        viewModelScope.launch {
            _movieResponse.postValue(mainRepository.mF())
            response.value = mainRepository.mF().body()
        }
    }
}