package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repository.TableRepository

class MarkTableAsPaid(private val tableRepository: TableRepository) {
    suspend operator fun invoke(tableId: Int) {
        tableRepository.changeTableState(tableId, "PAID")
    }
}
