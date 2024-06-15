package com.example.sportspot.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {


    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[UID_KEY] = user.uid
            preferences[EMAIL_KEY] = user.email
            preferences[DISPLAYNAME_KEY] = user.displayName
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }
    suspend fun saveAuthToken(data: UserModel) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = data.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[UID_KEY].toString(),
                preferences[EMAIL_KEY].toString(),
                preferences[DISPLAYNAME_KEY].toString(),
                preferences[TOKEN_KEY].toString(),
                preferences[IS_LOGIN_KEY] ?: false
            )
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val UID_KEY = stringPreferencesKey("ud")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val DISPLAYNAME_KEY = stringPreferencesKey("displayName")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}