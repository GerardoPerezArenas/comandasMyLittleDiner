package com.gerardo.comandas.data.repository

interface AuthRepository {
    suspend fun login(code: String): String? // Devuelve el nombre si es válido
    suspend fun getRole(): String
    suspend fun setRole(role: String)
}

