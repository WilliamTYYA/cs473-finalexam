package com.miu.finalexam.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import com.miu.finalexam.feature.main.data.Category

interface AppNavKey: NavKey
@Serializable
object Home: AppNavKey
@Serializable
data class ProductList(val category: Category): AppNavKey // Modify later => Enum
@Serializable
data class ProductDetail(val productId: Int): AppNavKey
@Serializable
object Settings: AppNavKey