package com.gerardo.comandas.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.ui.components.PantallaConRibetes
import kotlinx.coroutines.delay

@Composable
fun ZonasScreen(navController: NavController, name: String) {
    PantallaConRibetes(navController = navController) {
        val showGreeting = remember { androidx.compose.runtime.mutableStateOf(true) }
        LaunchedEffect(name) {
            showGreeting.value = true
            kotlinx.coroutines.delay(5000)
            showGreeting.value = false
        }
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
                AnimatedVisibility(
                    visible = showGreeting.value && name.isNotBlank(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Hola $name",
                        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                }
                Button(
                    onClick = { navController.navigate("barra_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Barra", color = Color.Black)
                }
                Button(
                    onClick = { navController.navigate("comedor_bar") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comedor Bar", color = Color.Black)
                }
                Button(
                    onClick = { navController.navigate("comedor_principal") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comedor Principal", color = Color.Black)
                }
            }
        }
    }
}
