package com.gerardo.comandas.domain.usecase

import com.gerardo.comandas.data.repository.OrderRepository
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine

class SendToPrinter(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(order: Order, lines: List<OrderLine>) {
        // Aquí iría la lógica para enviar el pedido a la impresora
    }
}

