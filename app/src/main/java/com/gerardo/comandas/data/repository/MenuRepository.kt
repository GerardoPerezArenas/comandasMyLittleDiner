package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.MenuItem
import com.gerardo.comandas.data.room.Category

interface MenuRepository {
    suspend fun getMenu(): List<MenuItem>
    suspend fun getCategories(): List<Category>
    suspend fun addMenuItem(item: MenuItem)
    suspend fun updateMenuItem(item: MenuItem)
    suspend fun deleteMenuItem(id: Int)
    suspend fun addCategory(category: Category)
    suspend fun updateCategory(category: Category)
    suspend fun deleteCategory(id: Int)
}

