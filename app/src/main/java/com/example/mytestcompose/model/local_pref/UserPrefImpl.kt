package com.example.mytestcompose.model.local_pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPrefImpl(private val dataStore: DataStore<Preferences>) : UserPref {
    override fun getUSerId(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USERID_KEY] ?: ""
        }
    }

    override fun getPassword(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[PASSWORD_KEY] ?: ""
        }
    }

    override fun getIsLogin(): Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[ISLOGIN_KEY] ?: false
        }
    }

    override suspend fun saveUSerId(userId: String) {
        dataStore.edit {
            it[USERID_KEY] = userId
        }
    }

    override suspend fun savePassword(password: String) {
        dataStore.edit {
            it[PASSWORD_KEY] = password
        }
    }

    override suspend fun saveIsLogin(isLogin: Boolean) {
        dataStore.edit {
            it[ISLOGIN_KEY]=isLogin
        }
    }
}