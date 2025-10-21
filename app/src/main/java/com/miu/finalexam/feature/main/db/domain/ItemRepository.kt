package com.miu.finalexam.feature.main.db.domain

import com.miu.finalexam.database.entity.Item
import com.miu.finalexam.feature.main.data.Category
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun insertItem(item: Item): Result<Unit>
    suspend fun updateItem(item: Item): Result<Unit>
    suspend fun deleteItem(item: Item): Result<Unit>
    fun getAllItems(): Flow<List<Item>>
    fun getItem(id: Int): Flow<Item>
    fun getItems(category: String): Flow<List<Item>>
}