package com.gerardo.comandas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaConRibetes(navController: NavController, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        RibeteCuadriculado()
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Atrás", color = Color.Black)
            }
            Button(
                onClick = { navController.navigate("main_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Inicio", color = Color.Black)
            }
        }
        RibeteCuadriculado()
    }
}
