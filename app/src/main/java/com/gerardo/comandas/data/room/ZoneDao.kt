package com.gerardo.comandas.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZoneDao {
    @Query("SELECT * FROM zones ORDER BY sort")
    suspend fun getAll(): List<Zone>

    @Insert
    suspend fun insertAll(zones: List<Zone>)
}

