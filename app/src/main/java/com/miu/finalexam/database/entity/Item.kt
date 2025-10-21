package com.miu.finalexam.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miu.finalexam.feature.main.data.Category

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val price: Double,
    val quantity: Int
)
