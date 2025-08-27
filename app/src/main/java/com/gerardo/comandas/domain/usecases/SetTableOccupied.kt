package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repository.TableRepository

class SetTableOccupied(private val tableRepository: TableRepository) {
    suspend operator fun invoke(tableId: Int) {
        tableRepository.changeTableState(tableId, "OCCUPIED")
    }
}
