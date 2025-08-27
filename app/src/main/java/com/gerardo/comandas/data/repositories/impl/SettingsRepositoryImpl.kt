package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.SettingsRepository

class SettingsRepositoryImpl : SettingsRepository {
    
    // Simple in-memory storage for settings
    // In a real application, this would likely use SharedPreferences,
    // Room database, or another persistent storage mechanism
    private val settings = mutableMapOf<String, String>()
    
    override fun getSetting(key: String): String? {
        return settings[key]
    }
    
    override fun setSetting(key: String, value: String) {
        settings[key] = value
    }
}