package com.miu.finalexam.retrofit.domain

import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto

interface JokeRepository {
    suspend fun getJoke(): Result<JokeResponseDto>
}