package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.R
import com.gerardo.comandas.ui.components.PantallaConRibetes

@Composable
fun ComidaScreen(navController: NavController) {
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
                val comidas: List<Triple<String, String, Int>> = listOf(
                    Triple("Hamburguesa", "Pan, carne, lechuga, tomate, queso", R.drawable.placeholder),
                    Triple("Pizza", "Masa, salsa de tomate, queso, pepperoni", R.drawable.placeholder),
                    Triple("Ensalada", "Lechuga, tomate, zanahoria, aderezo", R.drawable.placeholder)
                )
                for ((nombre, ingredientes, imagen) in comidas) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { navController.navigate("ingredientes_screen/$nombre/$ingredientes") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = imagen),
                            contentDescription = nombre,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = nombre,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
