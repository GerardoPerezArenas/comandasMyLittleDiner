package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    suspend fun getAll(): List<MenuItem>

    @Query("SELECT * FROM menu_items WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<MenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MenuItem>)
}
