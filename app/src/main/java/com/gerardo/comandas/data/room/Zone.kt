package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zones")
data class Zone(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
