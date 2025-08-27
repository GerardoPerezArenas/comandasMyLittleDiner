package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine

interface OrderRepository {
    suspend fun createOrder(order: Order): Long
    suspend fun getOrdersByTable(tableSpotId: Int): List<Order>
    suspend fun addOrderLine(orderLine: OrderLine)
    suspend fun getOrderLines(orderId: Int): List<OrderLine>
    suspend fun updateOrderLine(orderLine: OrderLine)
    suspend fun deleteOrderLine(id: Int)
}
