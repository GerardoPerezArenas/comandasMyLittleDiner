package com.gerardo.comandas.data.repositories

interface AuthRepository {
    fun login(username: String, password: String): Boolean
}

