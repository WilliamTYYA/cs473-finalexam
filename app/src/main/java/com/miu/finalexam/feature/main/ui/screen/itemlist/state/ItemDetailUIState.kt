package com.miu.finalexam.feature.main.ui.screen.itemlist.state

import com.miu.finalexam.database.entity.Item


data class ItemDetailUIState(
    val item: Item? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
