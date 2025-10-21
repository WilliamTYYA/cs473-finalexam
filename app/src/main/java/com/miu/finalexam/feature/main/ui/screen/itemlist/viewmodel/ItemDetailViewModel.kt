package com.miu.finalexam.feature.main.ui.screen.itemlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.finalexam.database.entity.Item
import com.miu.finalexam.feature.main.data.Category
import com.miu.finalexam.feature.main.data.Product
import com.miu.finalexam.feature.main.db.domain.ItemRepository
import com.miu.finalexam.feature.main.ui.screen.itemlist.state.ItemDetailUIState
import com.miu.finalexam.feature.main.ui.screen.itemlist.state.ItemListUiState
import com.miu.finalexam.feature.main.ui.screen.productdetail.ProductDetailUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val itemRepository: ItemRepository,
    private val itemId: Int
): ViewModel() {
    val itemUiState: StateFlow<ItemDetailUIState> = itemRepository.getItem(itemId)
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()
        .map { item: Item -> //Flow
            ItemDetailUIState(
                item = item
            )
        }
        .onStart {
            ItemDetailUIState(
                isLoading = true
            )
        }
        .catch { exception ->
            ItemListUiState(
                errorMessage = exception.message
            )
        }
        .stateIn( //its StateFlow
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ItemDetailUIState()
        )
}