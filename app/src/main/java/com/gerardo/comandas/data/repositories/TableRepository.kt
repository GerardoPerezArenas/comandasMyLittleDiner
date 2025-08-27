package com.gerardo.comandas.data.repositories

interface TableRepository {
    fun markAsPaid(tableId: Int)
    fun setOccupied(tableId: Int)
    fun setFree(tableId: Int)
}

