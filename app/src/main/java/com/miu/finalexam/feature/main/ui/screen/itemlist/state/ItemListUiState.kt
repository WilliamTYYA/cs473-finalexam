package com.miu.finalexam.feature.main.ui.screen.itemlist.state

import com.miu.finalexam.database.entity.Item

data class ItemListUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
