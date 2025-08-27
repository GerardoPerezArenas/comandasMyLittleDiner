package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.OrderRepository
import com.gerardo.comandas.data.room.OrderDao
import com.gerardo.comandas.data.room.OrderLineDao
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine
import kotlinx.coroutines.runBlocking

class OrderRepositoryImpl(
    private val orderDao: OrderDao,
    private val orderLineDao: OrderLineDao
) : OrderRepository {
    
    override fun createOrder(tableId: Int): Int {
        return runBlocking {
            val order = Order(tableId = tableId)
            orderDao.insert(order).toInt()
        }
    }
    
    override fun addOrderLine(orderId: Int, productId: Int, quantity: Int) {
        runBlocking {
            val orderLine = OrderLine(
                orderId = orderId,
                menuItemId = productId,
                quantity = quantity
            )
            orderLineDao.insertAll(listOf(orderLine))
        }
    }
    
    override fun sendToPrinter(orderId: Int) {
        // TODO: Implement printer functionality
        // For now, just log that the order is being sent to printer
        println("Order $orderId sent to printer")
    }
}