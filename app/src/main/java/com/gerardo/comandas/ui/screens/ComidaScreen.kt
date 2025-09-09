package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.R
import com.gerardo.comandas.data.room.OrderLine
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.ui.OrderViewModel
import com.gerardo.comandas.ui.components.PantallaConRibetes
import com.gerardo.comandas.ui.components.VoiceInputTextField
import kotlinx.coroutines.launch
import androidx.compose.runtime.produceState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerardo.comandas.data.room.MenuItem

@Composable
fun ComidaScreen(navController: NavController, mesaId: Int, orderId: Int?) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = RetroBurgerDatabase.getDatabase(context)
    val orderViewModel: OrderViewModel = viewModel()
    val menuItemsState = produceState<List<MenuItem>>(initialValue = emptyList(), producer = {
        value = db.menuItemDao().getByCategory(1)
    })
    val menuItems = menuItemsState.value
    var observacion by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(1) }
    PantallaConRibetes(navController = navController) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFC7F3E3)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Platos disponibles", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                LazyColumn {
                    items(menuItems) { menuItem ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.placeholder),
                                contentDescription = menuItem.name,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(menuItem.name, modifier = Modifier.weight(1f))
                            Text("${menuItem.priceCents / 100.0} €", modifier = Modifier.weight(1f))
                            TextField(
                                value = cantidad.toString(),
                                onValueChange = { value -> cantidad = value.toIntOrNull() ?: 1 },
                                label = { Text("Cantidad") },
                                modifier = Modifier.width(80.dp)
                            )
                            VoiceInputTextField(
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
