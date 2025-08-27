package com.gerardo.comandas.ui

import android.content.Context
import com.gerardo.comandas.data.repository.OrderRepositoryImpl
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.domain.usecase.AddOrderLine
import com.gerardo.comandas.domain.usecase.CreateOrder

class TestOrderProvider(context: Context) {
    private val db = RetroBurgerDatabase.getDatabase(context)
    private val orderRepository = OrderRepositoryImpl(db.orderDao(), db.orderLineDao())
    val createOrder = CreateOrder(orderRepository)
    val addOrderLine = AddOrderLine(orderRepository)
    suspend fun getOrders(tableId: Int) = orderRepository.getOrdersByTable(tableId)
    suspend fun getOrderLines(orderId: Int) = orderRepository.getOrderLines(orderId)
}

