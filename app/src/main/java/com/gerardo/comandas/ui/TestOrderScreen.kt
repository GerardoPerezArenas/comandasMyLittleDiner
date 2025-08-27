package com.gerardo.comandas.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine
import com.gerardo.comandas.domain.usecase.AddOrderLine
import com.gerardo.comandas.domain.usecase.CreateOrder
import kotlinx.coroutines.launch

@Composable
fun TestOrderScreen(
    createOrder: CreateOrder,
    addOrderLine: AddOrderLine,
    getOrders: suspend (Int) -> List<Order>,
    getOrderLines: suspend (Int) -> List<OrderLine>
) {
    val scope = rememberCoroutineScope()
    var tableId by remember { mutableStateOf(1) }
    var orderId by remember { mutableStateOf<Long?>(null) }
    var orders by remember { mutableStateOf<List<Order>>(emptyList()) }
    var orderLines by remember { mutableStateOf<List<OrderLine>>(emptyList()) }

    Column(Modifier.padding(16.dp)) {
        Text("Prueba de persistencia Room")
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                val newOrder = Order(tableSpotId = tableId, createdAt = System.currentTimeMillis())
                orderId = createOrder(newOrder)
                orders = getOrders(tableId)
            }
        }) {
            Text("Crear orden para mesa $tableId")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                orderId?.let {
                    val line = OrderLine(orderId = it.toInt(), itemId = 1, qty = 2, unitPriceCents = 1000, totalCents = 2000)
                    addOrderLine(line)
                    orderLines = getOrderLines(it.toInt())
                }
            }
        }, enabled = orderId != null) {
            Text("Añadir línea de pedido")
        }
        Spacer(Modifier.height(16.dp))
        Text("Órdenes:")
        orders.forEach { order ->
            Text("Orden ${order.id} - Estado: ${order.status} - Total: ${order.totalCents}")
        }
        Spacer(Modifier.height(8.dp))
        Text("Líneas de pedido:")
        orderLines.forEach { line ->
            Text("Línea ${line.id}: Item ${line.itemId}, Cantidad ${line.qty}, Total ${line.totalCents}")
        }
    }
}

