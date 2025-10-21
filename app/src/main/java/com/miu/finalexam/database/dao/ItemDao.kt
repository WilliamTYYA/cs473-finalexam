package com.miu.finalexam.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miu.finalexam.database.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: Item)
    @Update
    suspend fun updateItem(item: Item)
    @Delete
    suspend fun deleteItem(item: Item)
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
    @Query("SELECT * FROM items WHERE category = :category ORDER BY name ASC")
    fun getItems(category: String): Flow<List<Item>>
}