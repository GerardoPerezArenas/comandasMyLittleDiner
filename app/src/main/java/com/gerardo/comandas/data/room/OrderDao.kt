package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders WHERE tableSpotId = :tableSpotId")
    suspend fun getByTableSpot(tableSpotId: Int): List<Order>

    @Insert
    suspend fun insert(order: Order): Long

    @Query("UPDATE orders SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Int, status: String)
}

