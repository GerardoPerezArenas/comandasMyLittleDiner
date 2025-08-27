package com.gerardo.comandas.domain.usecase

import com.gerardo.comandas.data.repository.OrderRepository
import com.gerardo.comandas.data.room.Order

class CreateOrder(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(order: Order): Long = orderRepository.createOrder(order)
}

