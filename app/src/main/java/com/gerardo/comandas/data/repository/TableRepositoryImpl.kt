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
    override suspend fun changeTableState(tableId: Int, state: String) { /* Implementar cambio de estado */ }
}

