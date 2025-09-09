package com.gerardo.comandas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.gerardo.comandas.ui.components.VoiceInputTextField
import com.gerardo.comandas.ui.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    PantallaConRibetes(navController = navController) {
        val code = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }
        val users = mapOf(
            "117" to "Ruben",
            "126" to "Javi",
            "108" to "Evelyn"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7F3E3)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            VoiceInputTextField(
                value = code.value,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        code.value = input
                    }
                },
                placeholder = {
                    Text(
                        "Identifícate",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    onLogin(code.value, message, navController, authViewModel, users)
                }),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp)
            )
            androidx.compose.material3.Button(
                onClick = {
                    onLogin(code.value, message, navController, authViewModel, users)
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = "Ingresar",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
            if (message.value.isNotEmpty()) {
                LaunchedEffect(message.value) {
                    delay(5000)
                    message.value = ""
                }
                Text(
                    text = message.value,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

private fun onLogin(
    code: String,
    message: MutableState<String>,
    navController: NavController,
    authViewModel: AuthViewModel,
    users: Map<String, String>
) {
    val name = users[code]
    if (name == null) {
        message.value = "Identificación no válida"
        return
    }
    authViewModel.login(code)
    navController.navigate("zonas_screen?name=$name")
    message.value = ""
}
