package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tableSpotId: Int,
    val createdAt: Long,
    val status: String = "OPEN", // OPEN, SENT, PAID, CANCELLED
    val totalCents: Int = 0
)

