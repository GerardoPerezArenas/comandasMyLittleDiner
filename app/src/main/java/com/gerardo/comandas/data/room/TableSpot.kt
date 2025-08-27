package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_spots")
data class TableSpot(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val zoneId: Int,
    val name: String,
    val sort: Int = 0,
    val state: String = "FREE" // FREE, OCCUPIED, PAID
)

