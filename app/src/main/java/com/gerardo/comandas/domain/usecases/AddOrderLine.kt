package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.OrderRepository

class AddOrderLine(private val orderRepository: OrderRepository) {
    operator fun invoke(orderId: Int, productId: Int, quantity: Int) {
        orderRepository.addOrderLine(orderId, productId, quantity)
    }
}

