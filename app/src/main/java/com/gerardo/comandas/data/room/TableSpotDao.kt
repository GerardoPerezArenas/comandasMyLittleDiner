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
    
    @Query("UPDATE table_spots SET status = :status WHERE id = :tableId")
    suspend fun updateTableStatus(tableId: Int, status: String)
    
    @Query("SELECT * FROM table_spots WHERE id = :tableId")
    suspend fun getById(tableId: Int): TableSpot?
}
