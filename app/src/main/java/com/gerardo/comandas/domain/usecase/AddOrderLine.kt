package com.gerardo.comandas.domain.usecase

import com.gerardo.comandas.data.repository.OrderRepository
import com.gerardo.comandas.data.room.OrderLine

class AddOrderLine(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(orderLine: OrderLine) = orderRepository.addOrderLine(orderLine)
}

