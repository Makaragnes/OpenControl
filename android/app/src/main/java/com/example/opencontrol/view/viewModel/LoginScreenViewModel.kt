package com.example.opencontrol.view.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opencontrol.model.AuthModel
import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.example.DogResponse
import com.example.opencontrol.obj.DataObj
import com.example.opencontrol.repository.AuthRepository
import com.example.opencontrol.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    var response: MutableState<DogResponse?> = mutableStateOf(null)

    var resp: MutableState<OneStrModel?> = mutableStateOf(null)

    private var _movieResponse = MutableLiveData<Response<DogResponse>>()
    val movieResponse: LiveData<Response<DogResponse>> = _movieResponse

    val availableServer = mutableStateOf(false)

    val login = mutableStateOf("")
    val password = mutableStateOf("")

    fun getHome() {
        viewModelScope.launch {
            try {
                resp.value = mainRepository.getHome().body()
                availableServer.value = true
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    suspend fun auth(){
        DataObj.auth.value = authRepository.auth(AuthModel(
            login.value,
            password.value,
            0
        )).body()
        Log.d("QWER", DataObj.auth.value?.id.toString())
    }

    suspend fun validate(){
        DataObj.auth.value = authRepository.validate(AuthModel(
            login.value,
            password.value,
            0
        )).body()
        Log.d("QWER", DataObj.auth.value?.id.toString())
    }

//    fun getData() {
//        viewModelScope.launch {
//            _movieResponse.postValue(mainRepository.mF())
//            response.value = mainRepository.mF().body()
//        }
//    }
}