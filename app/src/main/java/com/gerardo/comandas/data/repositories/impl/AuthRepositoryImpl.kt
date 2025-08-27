package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    
    // Hardcoded users for demonstration purposes
    // In a real application, this would likely check against a database
    // or external authentication service
    private val users = mapOf(
        "171" to "Admin",
        "222" to "Javi", 
        "333" to "Maria",
        "444" to "Ander"
    )
    
    override fun login(username: String, password: String): Boolean {
        // For this demo, we're only using username/code authentication
        // The password parameter is currently not used but could be implemented
        return users.containsKey(username)
    }
}