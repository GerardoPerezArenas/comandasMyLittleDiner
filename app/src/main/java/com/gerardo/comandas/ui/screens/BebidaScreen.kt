package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.ui.components.PantallaConRibetes

@Composable
fun BebidaScreen(navController: NavController, showButtons: Boolean = true) {
    PantallaConRibetes(navController = navController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7F3E3)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val bebidas = listOf(
                    "Coca Cola" to "Refresco gaseoso",
                    "Agua Mineral" to "Agua con gas",
                    "Jugo de Naranja" to "Jugo natural de naranja",
                    "Cerveza" to "Bebida alcohólica fermentada"
                )
                for ((nombre, descripcion) in bebidas) {
                    if (showButtons) {
                        Button(
                            onClick = { navController.navigate("detalle_bebida_screen/$nombre/$descripcion") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = nombre, color = Color.Black)
                        }
                    } else {
                        Text(text = nombre, color = Color.Black, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}
