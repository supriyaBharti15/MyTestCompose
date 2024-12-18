package com.example.mytestcompose.model.local_pref

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USERID_KEY= stringPreferencesKey("user_id")
val PASSWORD_KEY= stringPreferencesKey("password")
val ISLOGIN_KEY= booleanPreferencesKey("isLogin")
interface UserPref {
    fun getUSerId(): Flow<String>
    fun getPassword(): Flow<String>
    fun getIsLogin(): Flow<Boolean>
    suspend fun saveUSerId(userId:String)
    suspend fun savePassword(password:String)
    suspend fun saveIsLogin(isLogin:Boolean)
}