package com.miu.finalexam.feature.login.data.local

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import com.miu.finalexam.feature.login.domain.model.UserCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.sql.DataSource

class PreferencesDataSource(val context: Context) {
    suspend fun saveUserCredential(credential: UserCredential) {
        // get the datastore object and write username and password in datastore object
        context.dataStore.edit { preferences: MutablePreferences ->
            preferences[DataStoreKeys.USER_NAME] = credential.username
            preferences[DataStoreKeys.PASSWORD] = credential.password
        }
    }

    fun getUserCredential(): Flow<UserCredential?> {
        return context.dataStore // DataStore<Preferences>
            .data // Flow<Preferences>
            .map { preferences ->
                val username = preferences[DataStoreKeys.USER_NAME]
                val password = preferences[DataStoreKeys.PASSWORD]
                if (username != null && password != null) {
                    UserCredential(username, password)
                } else {
                    null
                }
            }.distinctUntilChanged()
    }
}