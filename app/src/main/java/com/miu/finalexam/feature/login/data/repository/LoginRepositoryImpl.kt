package com.miu.finalexam.feature.login.data.repository

import com.miu.finalexam.feature.login.data.local.PreferencesDataSource
import com.miu.finalexam.feature.login.domain.model.UserCredential
import com.miu.finalexam.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val preferencesDataSource: PreferencesDataSource
): LoginRepository {
    override suspend fun saveUserCredential(credential: UserCredential) {
        preferencesDataSource.saveUserCredential(credential)
    }

    override fun getUserCredential(): Flow<UserCredential?> {
        return preferencesDataSource.getUserCredential()
    }
}