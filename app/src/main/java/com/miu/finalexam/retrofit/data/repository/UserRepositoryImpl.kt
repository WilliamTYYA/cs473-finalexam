package com.miu.finalexam.retrofit.data.repository


import com.miu.finalexam.retrofit.data.remote.api.JokeApiService
import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto
import com.miu.finalexam.retrofit.domain.JokeRepository

class JokeRepositoryImpl(
    private val jokeApiService: JokeApiService
): JokeRepository {

    override suspend fun getJoke(): Result<JokeResponseDto> {
        return runCatching {
            jokeApiService.getJoke()
        }
    }
}