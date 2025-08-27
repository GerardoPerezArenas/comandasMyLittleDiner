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
import com.gerardo.comandas.ui.components.PantallaConRibetes

@Composable
fun BebidaScreen(navController: NavController) {
    PantallaConRibetes(navController = navController) {
        var bebidaSeleccionada by remember { mutableStateOf<String?>(null) }
        var observacion by remember { mutableStateOf("") }
        var mostrarObservacion by remember { mutableStateOf(false) }
        val bebidas = listOf(
            "Coca Cola" to "Refresco gaseoso",
            "Agua Mineral" to "Agua con gas",
            "Jugo de Naranja" to "Jugo natural de naranja",
            "Cerveza" to "Bebida alcohólica fermentada"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7F3E3)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Selecciona una bebida:", modifier = Modifier.padding(16.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(bebidas) { (nombre, descripcion) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    bebidaSeleccionada = nombre
                                    mostrarObservacion = false
                                    observacion = ""
                                }
                                .background(if (bebidaSeleccionada == nombre) Color(0xFFB3E5FC) else Color.Transparent)
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(text = nombre, color = Color.Black)
                                Text(text = descripcion, color = Color.DarkGray, modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }
                }
                if (bebidaSeleccionada != null) {
                    Button(
                        onClick = { mostrarObservacion = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Observaciones", color = Color.White)
                    }
                }
                if (mostrarObservacion && bebidaSeleccionada != null) {
                    TextField(
                        value = observacion,
                        onValueChange = { observacion = it },
                        label = { Text("Escribe observaciones para ${bebidaSeleccionada}") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(16.dp)
                    )
                    if (observacion.isNotBlank()) {
                        Text(
                            text = "Observación: $observacion",
                            color = Color.DarkGray,
                            modifier = Modifier.padding(8.dp)
                        )
                        Button(
                            onClick = {
                                // Aquí se enviaría la bebida y la observación a la impresora
                                // Por ahora solo se limpia el estado para simular el envío
                                bebidaSeleccionada = null
                                observacion = ""
                                mostrarObservacion = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Enviar a impresora", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
