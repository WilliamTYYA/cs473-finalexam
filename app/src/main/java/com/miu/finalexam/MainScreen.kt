package com.miu.finalexam

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.finalexam.feature.login.data.local.PreferencesDataSource
import com.miu.finalexam.feature.login.data.repository.LoginRepositoryImpl
import com.miu.finalexam.feature.login.ui.screen.LoginScreen
import com.miu.finalexam.feature.login.ui.viewmodel.LoginViewModel
import com.miu.finalexam.nav.AppNavGraph

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val loginViewModel: LoginViewModel = viewModel {
        LoginViewModel(
            LoginRepositoryImpl(
                PreferencesDataSource(context)
            )
        )
    }
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    if (loginUiState.loginSuccess) {
        AppNavGraph()
    } else {
        LoginScreen()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MainScreen() {

}