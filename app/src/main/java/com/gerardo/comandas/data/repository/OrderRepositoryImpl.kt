package com.gerardo.comandas.data.repository

import com.gerardo.comandas.data.room.OrderLineDao
import com.gerardo.comandas.data.room.OrderDao
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine

class OrderRepositoryImpl(
    private val orderDao: OrderDao,
    private val orderLineDao: OrderLineDao
) : OrderRepository {
    override suspend fun createOrder(order: Order) = orderDao.insert(order)
    override suspend fun getOrdersByTable(tableSpotId: Int) = orderDao.getByTableSpot(tableSpotId)
    override suspend fun addOrderLine(orderLine: OrderLine) = orderLineDao.insertAll(listOf(orderLine))
    override suspend fun getOrderLines(orderId: Int) = orderLineDao.getByOrder(orderId)
    override suspend fun updateOrderLine(orderLine: OrderLine) = orderLineDao.insertAll(listOf(orderLine))
    override suspend fun deleteOrderLine(id: Int) = orderLineDao.deleteById(id)
}
