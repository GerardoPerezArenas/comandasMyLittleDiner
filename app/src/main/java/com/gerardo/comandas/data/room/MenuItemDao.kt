package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<MenuItem>

    @Insert
    suspend fun insertAll(menuItems: List<MenuItem>)

    @Query("DELETE FROM menu_items WHERE id = :id")
    suspend fun deleteById(id: Int)
}
