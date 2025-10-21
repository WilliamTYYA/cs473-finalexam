package com.miu.finalexam.feature.main.ui.screen.itemlist.state

data class ItemEditorUiState(
    val id: Int = 0,
    val name: String = "",
    val category: String = "",
    val price: String = "",
    val quantity: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean? = null
) {
    companion object {
        val Empty = ItemEditorUiState()
    }
}
