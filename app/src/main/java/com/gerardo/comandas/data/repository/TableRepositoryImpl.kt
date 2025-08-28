package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.ZoneDao
import com.gerardo.comandas.data.room.TableSpotDao
import com.gerardo.comandas.data.room.Zone
import com.gerardo.comandas.data.room.TableSpot

class TableRepositoryImpl(
    private val zoneDao: ZoneDao,
    private val tableSpotDao: TableSpotDao
) : TableRepository {
    override suspend fun getZones() = zoneDao.getAll()
    override suspend fun getTablesByZone(zoneId: Int) = tableSpotDao.getByZone(zoneId)
    override suspend fun updateTable(table: TableSpot) = tableSpotDao.insertAll(listOf(table))

    // Implementación añadida para actualizar el estado de la mesa
    override suspend fun changeTableState(tableId: Int, state: String) {
        tableSpotDao.updateState(tableId, state)
    }
}


