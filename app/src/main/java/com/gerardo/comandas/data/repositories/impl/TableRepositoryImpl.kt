package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.TableRepository
import com.gerardo.comandas.data.room.TableSpotDao
import kotlinx.coroutines.runBlocking

class TableRepositoryImpl(
    private val tableSpotDao: TableSpotDao
) : TableRepository {
    
    override fun markAsPaid(tableId: Int) {
        runBlocking {
            tableSpotDao.updatePaidStatus(tableId, true)
        }
    }
    
    override fun setOccupied(tableId: Int) {
        runBlocking {
            tableSpotDao.updateOccupiedStatus(tableId, true)
        }
    }
    
    override fun setFree(tableId: Int) {
        runBlocking {
            tableSpotDao.updateOccupiedStatus(tableId, false)
            // When a table is set free, it's also not paid
            tableSpotDao.updatePaidStatus(tableId, false)
        }
    }
}