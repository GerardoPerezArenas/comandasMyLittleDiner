package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gerardo.comandas.ui.components.PantallaConRibetes

@Composable
fun LoginScreen(navController: NavController) {
    PantallaConRibetes(navController = navController) {
        val code = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }
        val users = mapOf(
            "111" to "Admin",
            "222" to "Javi",
            "333" to "Maria",
            "444" to "Ander"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7F3E3)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = code.value,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        code.value = input
                    }
                },
                placeholder = { Text("Ingrese número", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    validateCodeAndNavigate(code.value, users, message, navController)
                }),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp)
            )
            Button(
                onClick = {
                    validateCodeAndNavigate(code.value, users, message, navController)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp)
            ) {
                Text("Ingresar", color = Color.White)
            }
            if (message.value.isNotEmpty()) {
                Text(message.value, color = Color.Red)
            }
        }
    }
}

private fun validateCodeAndNavigate(
    code: String,
    users: Map<String, String>,
    message: MutableState<String>,
    navController: NavController
) {
    val user = users[code]
    if (user != null) {
        message.value = "Bienvenido, $user"
        navController.navigate("zonas_screen")
    } else {
        message.value = "Código incorrecto"
    }
}
