package com.gerardo.comandas.data.repositories

interface SettingsRepository {
    fun getSetting(key: String): String?
    fun setSetting(key: String, value: String)
}

