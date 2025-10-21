package com.miu.finalexam.feature.main.ui.screen.productlist

import com.miu.finalexam.feature.main.data.Product

data class ProductListUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
