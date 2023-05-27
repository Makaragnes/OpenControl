package com.example.opencontrol.repository

import com.example.opencontrol.DogService
import com.example.opencontrol.model.example.DogResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(private val apiService: DogService) {

//    suspend fun getPopularMovies() = flow {
//        emit(NetworkResult.Loading(true))
//        val response = apiService.getDog()
//        emit(NetworkResult.Success(response.isSuccessful))
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }

    suspend fun mF(): Response<DogResponse> {
        return apiService.getDog()
    }
}