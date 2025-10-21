package com.miu.finalexam.feature.main.ui.screen.itemlist.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.finalexam.database.InventoryDatabase
import com.miu.finalexam.feature.main.data.ProductRepositoryImpl
import com.miu.finalexam.feature.main.db.data.ItemRepositoryImpl
import com.miu.finalexam.feature.main.ui.screen.itemlist.viewmodel.ItemDetailViewModel
import com.miu.finalexam.feature.main.ui.screen.itemlist.viewmodel.ItemListViewModel

@Composable
fun ItemDetailScreen(itemId: Int, modifier: Modifier = Modifier) {
    // we need app context
    val context = LocalContext.current
    val applicationContext = context.applicationContext
    //Get the db instance
    val database: InventoryDatabase = remember(applicationContext) {
        InventoryDatabase.getDatabase(applicationContext)
    }
    // get the dao instance
    val itemDao = remember { database.itemDao() }

    val viewModel: ItemDetailViewModel = viewModel {
        ItemDetailViewModel(
            ItemRepositoryImpl(itemDao), itemId
        )
    }
    val itemUiState by viewModel.itemUiState.collectAsStateWithLifecycle()

    Column {
        ListItem(
            headlineContent = {
                Text(text=itemUiState.item?.name ?: "Loading")
            },
            supportingContent = {
                Text(text=itemUiState.item?.category ?: "Loading...")
            },
            trailingContent = {
                Text(text="$${itemUiState.item?.price ?: "Loading..."}")
            }
        )
    }
}