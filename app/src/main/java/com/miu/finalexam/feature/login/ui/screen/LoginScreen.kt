package com.miu.finalexam.feature.login.ui.screen

import android.graphics.Outline
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.finalexam.feature.login.data.local.PreferencesDataSource
import com.miu.finalexam.feature.login.data.repository.LoginRepositoryImpl
import com.miu.finalexam.feature.login.ui.viewmodel.LoginViewModel
import org.w3c.dom.Text

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val loginViewModel: LoginViewModel = viewModel {
        LoginViewModel(
            LoginRepositoryImpl(
                PreferencesDataSource(context)
            )
        )
    }
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(modifier=modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center, // Arrange children vertically in the center
            horizontalAlignment = Alignment.CenterHorizontally // Align children horizontally in the center
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier=Modifier.padding(8.dp))

            OutlinedTextField(
                value = loginUiState.username,
                onValueChange = {
                    loginViewModel.onUsernameChange(it)
                },
                label = { Text("Username") }
            )
            Spacer(modifier=Modifier.padding(4.dp))
            OutlinedTextField(
                value = loginUiState.password,
                onValueChange = {
                    loginViewModel.onPasswordChange(it)
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier=Modifier.padding(8.dp))
            Button(
                modifier = modifier,
                onClick = {
                    loginViewModel.login()
                },
            ) {
                Text("Login")
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}