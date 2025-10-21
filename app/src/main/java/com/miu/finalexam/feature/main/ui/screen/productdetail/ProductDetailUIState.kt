package com.miu.finalexam.feature.main.ui.screen.productdetail

import com.miu.finalexam.feature.main.data.Product

data class ProductDetailUIState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {

}
