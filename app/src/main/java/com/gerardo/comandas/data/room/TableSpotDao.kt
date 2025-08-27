package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TableSpotDao {
    @Query("SELECT * FROM table_spots")
    suspend fun getAll(): List<TableSpot>

    @Query("SELECT * FROM table_spots WHERE zoneId = :zoneId")
    suspend fun getByZone(zoneId: Int): List<TableSpot>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tables: List<TableSpot>)
}
