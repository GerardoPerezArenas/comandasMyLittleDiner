package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.ui.components.PantallaConRibetes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerardo.comandas.ui.TableViewModel
import com.gerardo.comandas.ui.OrderViewModel
import com.gerardo.comandas.di.AppModule
import com.gerardo.comandas.data.repository.OrderRepositoryImpl
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.data.room.Order
import com.gerardo.comandas.data.room.OrderLine
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.material3.Button

@Composable
fun BarraComerAquiMesaScreen(navController: NavController, mesaId: Int) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = RetroBurgerDatabase.getDatabase(context)
    val tableRepository = AppModule.provideTableRepository(context)
    val orderRepository = OrderRepositoryImpl(db.orderDao(), db.orderLineDao())
    val tableViewModel: TableViewModel = viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return TableViewModel(tableRepository) as T
        }
    })
    val orderViewModel: OrderViewModel = viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(orderRepository) as T
        }
    })
    val orderLinesState = orderViewModel.orderLines.collectAsState()
    val pedidoCreado = remember { androidx.compose.runtime.mutableStateOf(false) }
    val pedidoId = remember { androidx.compose.runtime.mutableStateOf<Long?>(null) }

    PantallaConRibetes(navController = navController) {
        LaunchedEffect(Unit) {
            if (!pedidoCreado.value) {
                tableViewModel.changeTableState(mesaId, "OCCUPIED")
                val pedido = Order(tableSpotId = mesaId, createdAt = System.currentTimeMillis())
                // La función createOrder es suspend, así que debe llamarse dentro de LaunchedEffect
                val pedidoIdCreado = orderViewModel.createOrder(pedido)
                pedidoId.value = pedidoIdCreado
                pedidoCreado.value = true
                pedidoId.value?.let { orderViewModel.loadOrderLines(it.toInt()) }
            } else {
                pedidoId.value?.let { orderViewModel.loadOrderLines(it.toInt()) }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFC7F3E3)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Barra $mesaId", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                Text("Pedido actual:")
                orderLinesState.value.forEach { line ->
                    Text("${line.qty} x Item ${line.itemId} - Total: ${line.totalCents}")
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    // Añadir producto de ejemplo y enviar a impresora
                    pedidoId.value?.let {
                        val nuevo = OrderLine(orderId = it.toInt(), itemId = 1, qty = 1, unitPriceCents = 950, totalCents = 950)
                        orderViewModel.addOrderLine(nuevo)
                        // Aquí iría la lógica para enviar solo lo nuevo a la impresora
                    }
                }) {
                    Text("Añadir producto y enviar a impresora")
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = {
                    // Cerrar pedido y liberar mesa
                    tableViewModel.changeTableState(mesaId, "FREE")
                    navController.popBackStack()
                }) {
                    Text("Cerrar pedido y liberar mesa")
                }
            }
        }
    }
}
