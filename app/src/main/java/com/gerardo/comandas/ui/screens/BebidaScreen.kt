package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.data.room.OrderLine
import com.gerardo.comandas.ui.OrderViewModel
import com.gerardo.comandas.ui.components.PantallaConRibetes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.produceState
import com.gerardo.comandas.data.room.MenuItem

@Composable
fun BebidaScreen(navController: NavController, mesaId: Int, orderId: Int?) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = RetroBurgerDatabase.getDatabase(context)
    val orderViewModel: OrderViewModel = viewModel()
    val menuItemsState = produceState<List<MenuItem>>(initialValue = emptyList(), producer = {
        value = db.menuItemDao().getByCategory(2)
    })
    val menuItems = menuItemsState.value
    var observacion by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(1) }
    PantallaConRibetes(navController = navController) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFC7F3E3)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Bebidas disponibles", modifier = Modifier.padding(16.dp))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(menuItems) { menuItem ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(menuItem.name, modifier = Modifier.weight(1f))
                            Text("${menuItem.priceCents / 100.0} €", modifier = Modifier.weight(1f))
                            TextField(
                                value = cantidad.toString(),
                                onValueChange = { value -> cantidad = value.toIntOrNull() ?: 1 },
                                label = { Text("Cantidad") },
                                modifier = Modifier.width(80.dp)
                            )
                            TextField(
                                value = observacion,
                                onValueChange = { observacion = it },
                                label = { Text("Nota") },
                                modifier = Modifier.width(120.dp)
                            )
                            Button(onClick = {
                                if (orderId != null) {
                                    orderViewModel.addOrderLine(
                                        OrderLine(
                                            orderId = orderId,
                                            itemId = menuItem.id,
                                            qty = cantidad,
                                            note = observacion,
                                            unitPriceCents = menuItem.priceCents,
                                            totalCents = menuItem.priceCents * cantidad
                                        )
                                    )
                                }
                            }) {
                                Text("Añadir")
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    // Aquí iría la lógica para enviar a impresora y actualizar estado
                }) {
                    Text("Enviar a impresora")
                }
            }
        }
    }
}
