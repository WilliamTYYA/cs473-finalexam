package com.miu.finalexam.feature.main.ui.screen.home

import com.miu.finalexam.feature.main.data.Category

data class HomeUIState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
