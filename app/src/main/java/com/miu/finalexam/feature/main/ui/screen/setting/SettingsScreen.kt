package com.miu.finalexam.feature.main.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.miu.finalexam.feature.main.data.Setting

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,

) {
    Column {
        Setting.entries.toList().forEach { setting: Setting ->
            Text(text = setting.name)
        }
    }
}