package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TableSpotDao {
    @Query("SELECT * FROM table_spots WHERE zoneId = :zoneId ORDER BY sort")
    suspend fun getByZone(zoneId: Int): List<TableSpot>

    @Insert
    suspend fun insertAll(tableSpots: List<TableSpot>)

    @Query("UPDATE table_spots SET state = :state WHERE id = :id")
    suspend fun updateState(id: Int, state: String)
}

