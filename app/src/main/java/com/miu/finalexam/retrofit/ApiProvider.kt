package com.miu.finalexam.retrofit

import com.miu.finalexam.retrofit.data.remote.api.JokeApiService
import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ApiProvider {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val jokeApiService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}