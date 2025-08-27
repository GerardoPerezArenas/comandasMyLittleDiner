package com.gerardo.comandas.data.repositories

interface OrderRepository {
    fun createOrder(tableId: Int): Int
    fun addOrderLine(orderId: Int, productId: Int, quantity: Int)
    fun sendToPrinter(orderId: Int)
    // Puedes agregar otros métodos según sea necesario
}
