package com.gerardo.comandas.data.repositories.impl

import org.junit.Test
import org.junit.Assert.*

/**
 * Test for repository implementations to verify they exist and compile correctly
 */
class RepositoryImplementationsTest {
    @Test
    fun testRepositoryClassesExist() {
        // Verify that all repository implementation classes can be referenced
        // This ensures they compile correctly even if we can't run full integration tests
        
        val orderRepoClass = OrderRepositoryImpl::class.java
        val tableRepoClass = TableRepositoryImpl::class.java
        val menuRepoClass = MenuRepositoryImpl::class.java
        val settingsRepoClass = SettingsRepositoryImpl::class.java
        
        assertNotNull("OrderRepositoryImpl should exist", orderRepoClass)
        assertNotNull("TableRepositoryImpl should exist", tableRepoClass)
        assertNotNull("MenuRepositoryImpl should exist", menuRepoClass)
        assertNotNull("SettingsRepositoryImpl should exist", settingsRepoClass)
        
        // Verify they have the expected methods
        assertTrue("OrderRepositoryImpl should implement OrderRepository", 
            com.gerardo.comandas.data.repositories.OrderRepository::class.java.isAssignableFrom(orderRepoClass))
        assertTrue("TableRepositoryImpl should implement TableRepository", 
            com.gerardo.comandas.data.repositories.TableRepository::class.java.isAssignableFrom(tableRepoClass))
    }
}