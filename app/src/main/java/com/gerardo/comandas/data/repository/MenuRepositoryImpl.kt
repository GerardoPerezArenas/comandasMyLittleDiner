package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.MenuItemDao
import com.gerardo.comandas.data.room.CategoryDao
import com.gerardo.comandas.data.room.MenuItem
import com.gerardo.comandas.data.room.Category

class MenuRepositoryImpl(
    private val menuItemDao: MenuItemDao,
    private val categoryDao: CategoryDao
) : MenuRepository {
    override suspend fun getMenu() = menuItemDao.getByCategory(1) + menuItemDao.getByCategory(2)
    override suspend fun getCategories() = categoryDao.getAll()
    override suspend fun addMenuItem(item: MenuItem) = menuItemDao.insertAll(listOf(item))
    override suspend fun updateMenuItem(item: MenuItem) = menuItemDao.insertAll(listOf(item))
    override suspend fun deleteMenuItem(id: Int) = menuItemDao.deleteById(id)
    override suspend fun addCategory(category: Category) = categoryDao.insertAll(listOf(category))
    override suspend fun updateCategory(category: Category) = categoryDao.insertAll(listOf(category))
    override suspend fun deleteCategory(id: Int) = categoryDao.deleteById(id)
}
