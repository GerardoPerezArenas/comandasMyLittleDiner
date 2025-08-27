package com.gerardo.comandas.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class AuthRepositoryImpl(private val context: Context) : AuthRepository {
    private val ROLE_KEY = stringPreferencesKey("role")
    private val users = mapOf(
        "117" to "ADMIN",
        "126" to "Javi",
        "108" to "Evelyn"
    )
    override suspend fun login(code: String): String? {
        val role = users[code]
        if (role != null) {
            context.dataStore.edit { it[ROLE_KEY] = role }
        }
        return role
    }
    override suspend fun getRole(): String {
        val prefs = context.dataStore.data.first()
        return prefs[ROLE_KEY] ?: "USER"
    }
    override suspend fun setRole(role: String) {
        context.dataStore.edit { it[ROLE_KEY] = role }
    }
}

