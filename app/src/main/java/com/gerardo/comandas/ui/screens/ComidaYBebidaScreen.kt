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
fun ComidaYBebidaScreen(navController: NavController, mesaId: Int, orderId: Int?) {
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
                Text(
                    text = "Barra $mesaId",
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Button(
                    onClick = { navController.navigate("comida_screen?mesaId=$mesaId&orderId=$orderId") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comida", color = Color.Black)
                }
                Button(
                    onClick = { navController.navigate("bebida_screen?mesaId=$mesaId&orderId=$orderId") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Bebida", color = Color.Black)
                }
            }
        }
    }
}
