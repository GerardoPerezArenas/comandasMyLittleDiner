package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<Order>

    @Insert
    suspend fun insert(order: Order): Long
    
    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getById(orderId: Int): Order?
}
