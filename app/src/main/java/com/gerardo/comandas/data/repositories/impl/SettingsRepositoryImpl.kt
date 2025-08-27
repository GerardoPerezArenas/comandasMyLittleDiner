package com.gerardo.comandas.data.repositories.impl

import android.content.Context
import android.content.SharedPreferences
import com.gerardo.comandas.data.repositories.SettingsRepository

class SettingsRepositoryImpl(
    context: Context
) : SettingsRepository {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    
    override fun getSetting(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
    
    override fun setSetting(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }
}