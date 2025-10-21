package com.miu.finalexam.feature.main.ui.screen.home.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.finalexam.retrofit.data.remote.dto.response.JokeResponseDto
import com.miu.finalexam.retrofit.domain.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel(
    private val jokeRepository: JokeRepository
): ViewModel() {
    private val _jokeUiState = MutableStateFlow(JokeUiState())
    val jokeUiState = _jokeUiState.asStateFlow()

    init {
        getJoke()
    }
    fun getJoke() {
        // launch a coroutine using ViewModelScope
        viewModelScope.launch {
            _jokeUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            //now, fetch the users: over the coroutine from main thread to worker thread
            val result: Result<JokeResponseDto> = withContext(Dispatchers.IO) {
                jokeRepository.getJoke()
            }
            result.onSuccess { joke: JokeResponseDto? ->
                _jokeUiState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        joke = joke
                    )
                }
            }.onFailure { error: Throwable ->
                _jokeUiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }
}