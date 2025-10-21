package com.miu.finalexam.retrofit.data.remote.api

import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto
import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")
    suspend fun getJoke(): JokeResponseDto
}