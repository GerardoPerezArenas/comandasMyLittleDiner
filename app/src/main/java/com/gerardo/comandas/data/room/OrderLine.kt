package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_lines")
data class OrderLine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: Int,
    val itemId: Int,
    val qty: Int,
    val note: String? = null,
    val unitPriceCents: Int,
    val totalCents: Int
)

