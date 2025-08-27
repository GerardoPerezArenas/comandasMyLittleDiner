package com.gerardo.comandas.ui

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// Extensión de Context para obtener una instancia única de DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val ROLE_KEY = stringPreferencesKey("role")

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    init {
        viewModelScope.launch {
            getApplication<Application>().applicationContext.dataStore.data
                .map { prefs -> prefs[ROLE_KEY] }
                .collect { role ->
                    _state.value = AuthState(role)
                }
        }
    }

    fun login(code: String) {
        val role = if (code == "171") "ADMIN" else "USER"
        viewModelScope.launch {
            getApplication<Application>().applicationContext.dataStore.edit { prefs ->
                prefs[ROLE_KEY] = role
            }
            _state.value = AuthState(role)
        }
    }
}
