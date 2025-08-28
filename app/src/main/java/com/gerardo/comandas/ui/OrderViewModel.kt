package com.gerardo.comandas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardo.comandas.data.repository.OrderRepository
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    // Almacena el ID del pedido creado para la mesa
    private val _currentOrderId = MutableStateFlow<Long?>(null)
    val currentOrderId: StateFlow<Long?> = _currentOrderId

    /**
     * Crea un nuevo pedido para la mesa indicada y guarda su ID.
     */
    fun createOrderForTable(tableId: Int) {
        viewModelScope.launch {
            val order = Order(
                tableSpotId = tableId,
                createdAt = System.currentTimeMillis()
            )
            val id = orderRepository.createOrder(order)
            _currentOrderId.value = id
        }
    }
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    private val _orderLines = MutableStateFlow<List<OrderLine>>(emptyList())
    val orderLines: StateFlow<List<OrderLine>> = _orderLines

    suspend fun createOrder(order: Order): Long {
        return orderRepository.createOrder(order)
    }

    fun addOrderLine(orderLine: OrderLine) {
        viewModelScope.launch {
            orderRepository.addOrderLine(orderLine)
            loadOrderLines(orderLine.orderId)
        }
    }

    fun loadOrders(tableSpotId: Int) {
        viewModelScope.launch {
            _orders.value = orderRepository.getOrdersByTable(tableSpotId)
        }
    }

    fun loadOrderLines(orderId: Int) {
        viewModelScope.launch {
            _orderLines.value = orderRepository.getOrderLines(orderId)
        }
    }
}
