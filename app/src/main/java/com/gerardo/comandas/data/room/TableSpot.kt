package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_spots",
    foreignKeys = [ForeignKey(
        entity = Zone::class,
        parentColumns = ["id"],
        childColumns = ["zoneId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("zoneId")]
)
data class TableSpot(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,
    val zoneId: Int
)
