package com.gerardo.comandas.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class SettingsRepositoryImpl(private val context: Context) : SettingsRepository {
    private val PRINTER_TYPE_KEY = stringPreferencesKey("printer_type")
    private val PRINTER_ADDRESS_KEY = stringPreferencesKey("printer_address")
    private val TAX_KEY = doublePreferencesKey("tax")
    private val TIP_KEY = doublePreferencesKey("tip")

    override suspend fun getPrinterType(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PRINTER_TYPE_KEY] ?: ""
    }
    override suspend fun setPrinterType(type: String) {
        context.dataStore.edit { it[PRINTER_TYPE_KEY] = type }
    }
    override suspend fun getPrinterAddress(): String {
        val prefs = context.dataStore.data.first()
        return prefs[PRINTER_ADDRESS_KEY] ?: ""
    }
    override suspend fun setPrinterAddress(address: String) {
        context.dataStore.edit { it[PRINTER_ADDRESS_KEY] = address }
    }
    override suspend fun getTax(): Double {
        val prefs = context.dataStore.data.first()
        return prefs[TAX_KEY] ?: 0.0
    }
    override suspend fun setTax(tax: Double) {
        context.dataStore.edit { it[TAX_KEY] = tax }
    }
    override suspend fun getTip(): Double {
        val prefs = context.dataStore.data.first()
        return prefs[TIP_KEY] ?: 0.0
    }
    override suspend fun setTip(tip: Double) {
        context.dataStore.edit { it[TIP_KEY] = tip }
    }
}

