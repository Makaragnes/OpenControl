package com.example.opencontrol.repository

import com.example.opencontrol.model.OneStrModel
import com.example.opencontrol.model.departments.DepartmentModel
import com.example.opencontrol.service.DepartmentService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(
    private val apiService: DepartmentService
    ) {

//    suspend fun getPopularMovies() = flow {
//        emit(NetworkResult.Loading(true))
//        val response = apiService.getDog()
//        emit(NetworkResult.Success(response.isSuccessful))
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }

//    suspend fun mF(): Response<DogResponse> {
//        return apiService.getDog()
//    }

    suspend fun getDepartments(): Response<List<DepartmentModel>> {
        return apiService.getListOfDepartmens()
    }

    suspend fun getHome(): Response<OneStrModel> {
        return apiService.getHome()
    }




}