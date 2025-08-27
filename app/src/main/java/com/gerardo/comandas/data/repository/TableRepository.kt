package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.TableSpot
import com.gerardo.comandas.data.room.Zone

interface TableRepository {
    suspend fun getZones(): List<Zone>
    suspend fun getTablesByZone(zoneId: Int): List<TableSpot>
    suspend fun updateTable(table: TableSpot)
    suspend fun changeTableState(tableId: Int, state: String)
}

