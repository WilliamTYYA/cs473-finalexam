package com.miu.finalexam.feature.main.db.data

import com.miu.finalexam.database.dao.ItemDao
import com.miu.finalexam.database.entity.Item
import com.miu.finalexam.feature.main.db.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlin.runCatching

class ItemRepositoryImpl(
    private val itemDao: ItemDao
): ItemRepository {
    override suspend fun insertItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.insertItem(item)
        }
    }

    override suspend fun updateItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.updateItem(item)
        }
    }

    override suspend fun deleteItem(item: Item): Result<Unit> {
        return runCatching {
            itemDao.deleteItem(item)
        }
    }

    override fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItem(id: Int): Flow<Item> = itemDao.getItem(id)

    override fun getItems(category: String): Flow<List<Item>> = itemDao.getItems(category)

}