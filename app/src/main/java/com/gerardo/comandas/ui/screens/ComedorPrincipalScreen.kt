package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.ui.components.PantallaConRibetes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerardo.comandas.ui.TableViewModel
import com.gerardo.comandas.ui.OrderViewModel
import com.gerardo.comandas.data.repository.OrderRepositoryImpl
import com.gerardo.comandas.data.repository.TableRepositoryImpl
import com.gerardo.comandas.data.room.RetroBurgerDatabase

@Composable
fun ComedorPrincipalScreen(navController: NavController) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = RetroBurgerDatabase.getDatabase(context)
    val tableRepository = TableRepositoryImpl(db.zoneDao(), db.tableSpotDao())
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
    val tablesState = tableViewModel.tables.collectAsState()

    PantallaConRibetes(navController = navController) {
        // Zona 3 = Comedor Principal
        LaunchedEffect(Unit) {
            tableViewModel.loadTables(3)
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text("Mesas en Comedor Principal", modifier = Modifier.padding(8.dp))
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                tablesState.value.forEach { mesa ->
                    val color = when (mesa.state) {
                        "FREE" -> Color(0xFF4CAF50)
                        else -> Color(0xFFD32F2F)
                    }
                    Button(
                        onClick = {
                            if (mesa.state == "FREE") {
                                tableViewModel.changeTableState(mesa.id, "OCCUPIED")
                                orderViewModel.createOrderForTable(mesa.id)
                                navController.navigate("comida_y_bebida_screen?mesaId=${mesa.id}")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = color),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text("Mesa ${mesa.name}", color = Color.White)
                    }
                }
            }
        }
    }
}
