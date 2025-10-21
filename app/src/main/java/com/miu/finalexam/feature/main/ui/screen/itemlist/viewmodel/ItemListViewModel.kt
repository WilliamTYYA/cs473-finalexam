package com.miu.finalexam.feature.main.ui.screen.itemlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.finalexam.database.entity.Item
import com.miu.finalexam.feature.main.data.Category
import com.miu.finalexam.feature.main.db.domain.ItemRepository
import com.miu.finalexam.feature.main.ui.screen.itemlist.state.ItemListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemListViewModel(
    private val itemRepository: ItemRepository,
    category: Category
): ViewModel() {
    val itemListUiState: StateFlow<ItemListUiState> = itemRepository.getItems(category.toString())
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .map { items: List<Item> -> //Flow
                    ItemListUiState(
                        items = items
                    )
                }
                .onStart { //Flow<ItemListUiState>
                    ItemListUiState(
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
                    initialValue = ItemListUiState()
                )

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                itemRepository.deleteItem(item)
            }
        }
    }
}