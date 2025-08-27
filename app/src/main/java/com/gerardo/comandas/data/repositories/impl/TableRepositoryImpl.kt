package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.TableRepository
import com.gerardo.comandas.data.room.TableSpotDao
import kotlinx.coroutines.runBlocking

class TableRepositoryImpl(
    private val tableSpotDao: TableSpotDao
) : TableRepository {
    
    override fun markAsPaid(tableId: Int) {
        runBlocking {
            tableSpotDao.updateTableStatus(tableId, "PAID")
        }
    }
    
    override fun setOccupied(tableId: Int) {
        runBlocking {
            tableSpotDao.updateTableStatus(tableId, "OCCUPIED")
        }
    }
    
    override fun setFree(tableId: Int) {
        runBlocking {
            tableSpotDao.updateTableStatus(tableId, "FREE")
        }
    }
}