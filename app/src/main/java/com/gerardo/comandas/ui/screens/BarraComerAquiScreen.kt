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
fun BarraComerAquiScreen(navController: NavController) {
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
                for (i in 1..10) {
                    Button(
                        onClick = { navController.navigate("barra_comer_aqui_mesa_$i") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(text = "Barra $i", color = Color.Black)
                    }
                }
            }
        }
    }
}
