package com.miu.finalexam.feature.login.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.finalexam.feature.login.domain.model.UserCredential
import com.miu.finalexam.feature.login.domain.repository.LoginRepository
import com.miu.finalexam.feature.login.ui.state.LoginUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val loginCredential: Flow<UserCredential?> = loginRepository.getUserCredential()
            val existingCredential = loginCredential.firstOrNull()
            if (existingCredential?.username == "admin" && existingCredential?.password == "admin") {
                Log.i("LoginViewModel", "User already logged in")
            }
        }
    }

    fun onUsernameChange(username: String) {
        _loginUiState.update {
            it.copy(username = username)
        }
    }

    fun onPasswordChange(password: String) {
        _loginUiState.update {
            it.copy(password = password)
        }
    }

    fun login() {
        viewModelScope.launch {
            val username = loginUiState.value.username
            val password = loginUiState.value.password
            if (username == "admin" && password == "admin") {
                // write login crediential to database object(auth_prefs)
                loginRepository.saveUserCredential(UserCredential(username, password))
                // go to home screen / welcome screen
                Log.i("LoginViewModel", "Login successful")
                _loginUiState.update {
                    it.copy(loginSuccess = true)
                }
            } else {
                Log.i("LoginViewModel", "Login failed")
                _loginUiState.update {
                    it.copy(loginSuccess = false)
                }
            }
        }
    }
}