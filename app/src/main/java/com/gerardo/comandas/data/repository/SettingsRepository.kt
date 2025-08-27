package com.gerardo.comandas.data.repository

interface SettingsRepository {
    suspend fun getPrinterType(): String
    suspend fun setPrinterType(type: String)
    suspend fun getPrinterAddress(): String
    suspend fun setPrinterAddress(address: String)
    suspend fun getTax(): Double
    suspend fun setTax(tax: Double)
    suspend fun getTip(): Double
    suspend fun setTip(tip: Double)
}

