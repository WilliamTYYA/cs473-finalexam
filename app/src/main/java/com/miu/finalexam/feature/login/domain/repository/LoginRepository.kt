package com.miu.finalexam.feature.login.domain.repository

import com.miu.finalexam.feature.login.domain.model.UserCredential
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun saveUserCredential(credential: UserCredential)
    fun getUserCredential(): Flow<UserCredential?>
}