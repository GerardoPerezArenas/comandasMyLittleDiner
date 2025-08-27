package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.R
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Fondo primero
        Image(
            painter = painterResource(id = R.drawable.fondo_principal),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        // Color de fondo temporal para depuración
        Box(
            modifier = Modifier.matchParentSize().background(Color(0xFFE0E0E0))
        ) {}
        // Contenido principal encima del fondo
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a My Little Diner",
                modifier = Modifier.padding(top = 32.dp),
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_mylittlediner),
                        contentDescription = "Logo My Little Diner",
                        modifier = Modifier
                            .size(220.dp)
                            .clickable {
                                navController.navigate("login_screen")
                            },
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Toca el logo para comenzar",
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            androidx.compose.material3.Button(
                onClick = { navController.navigate("testOrder") },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Probar persistencia Room")
            }
        }
    }
}
