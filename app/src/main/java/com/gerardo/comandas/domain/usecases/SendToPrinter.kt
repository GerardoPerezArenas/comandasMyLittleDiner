package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.OrderRepository

class SendToPrinter(private val orderRepository: OrderRepository) {
    operator fun invoke(orderId: Int) {
        orderRepository.sendToPrinter(orderId)
    }
}

