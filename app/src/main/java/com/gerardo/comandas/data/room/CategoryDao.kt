package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<Category>

    @Insert
    suspend fun insertAll(categories: List<Category>)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteById(id: Int)
}
