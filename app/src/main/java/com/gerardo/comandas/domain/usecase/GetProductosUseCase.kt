package com.gerardo.comandas.domain.usecase

import com.gerardo.comandas.data.repository.ComandaRepository
import com.gerardo.comandas.domain.model.Producto

class GetProductosUseCase(private val repository: ComandaRepository) {
    operator fun invoke(): List<Producto> = repository.getProductos()
}
