package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderLineDao {
    @Query("SELECT * FROM order_lines WHERE orderId = :orderId")
    suspend fun getByOrder(orderId: Int): List<OrderLine>

    @Insert
    suspend fun insertAll(lines: List<OrderLine>)
    
    @Insert
    suspend fun insert(line: OrderLine): Long
}
