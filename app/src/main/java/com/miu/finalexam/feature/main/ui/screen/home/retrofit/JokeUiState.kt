package com.miu.finalexam.feature.main.ui.screen.home.retrofit

import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto

data class JokeUiState(
    val joke: JokeResponseDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)