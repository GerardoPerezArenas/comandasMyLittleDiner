package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.OrderRepository

class CreateOrder(private val orderRepository: OrderRepository) {
    operator fun invoke(tableId: Int): Int = orderRepository.createOrder(tableId)
}

