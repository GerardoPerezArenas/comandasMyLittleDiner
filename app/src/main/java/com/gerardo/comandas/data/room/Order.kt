package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "orders",
    foreignKeys = [ForeignKey(
        entity = TableSpot::class,
        parentColumns = ["id"],
        childColumns = ["tableId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("tableId")]
)
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tableId: Int,
    val timestamp: Long = System.currentTimeMillis()
)
