package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.TableRepository

class MarkTableAsPaid(private val tableRepository: TableRepository) {
    operator fun invoke(tableId: Int) {
        tableRepository.markAsPaid(tableId)
    }
}

