package com.example.opencontrol.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.opencontrol.model.AuthModel
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class AuthRepository @Inject constructor(
    private val departmentService: DepartmentService
) {
    //var response: MutableState<> = mutableStateOf(null)

    suspend fun auth(authModel: AuthModel): Response<AuthModel>{
        return departmentService.auth(authModel)
    }

    suspend fun validate(authModel: AuthModel): Response<AuthModel>{
        return departmentService.validate(authModel)
    }


}