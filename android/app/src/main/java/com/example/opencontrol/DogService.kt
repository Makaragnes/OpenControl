package com.example.opencontrol

import com.example.opencontrol.constants.Constants
import com.example.opencontrol.model.example.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogService {
    @GET(Constants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}