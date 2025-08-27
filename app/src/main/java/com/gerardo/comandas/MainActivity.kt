package com.gerardo.comandas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.text.BasicTextField
import com.gerardo.comandas.ui.components.PrimaryButton
import com.gerardo.comandas.ui.components.OutlinedField
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerardo.comandas.ui.AuthViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            }
        }
    }
}

@Composable
fun RibeteCuadriculado(modifier: Modifier = Modifier, squares: Int = 20) {
    Canvas(modifier = modifier.fillMaxWidth().height(32.dp)) {
        val squareWidth = size.width / squares
        for (i in 0 until squares) {
            drawRect(
                color = if (i % 2 == 0) Color.White else Color.Black,
                topLeft = androidx.compose.ui.geometry.Offset(i * squareWidth, 0f),
                size = Size(squareWidth, size.height),
                style = Fill
            )
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val authState by authViewModel.state.collectAsState()
    LaunchedEffect(authState.role) {
        if (authState.role != null) {
            navController.navigate("dashboard") {
                popUpTo("main_screen") { inclusive = true }
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE573A6)), // Rosa corporativo
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_mylittlediner),
                contentDescription = "Logo My Little Diner",
                modifier = Modifier
                    .size(220.dp)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("login_screen")
                    },
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "MY LITTLE DINER",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

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
            PrimaryButton(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Atrás", color = Color.Black)
            }
            PrimaryButton(
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

// Mejorar TextField y Button en LoginScreen
@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    PantallaConRibetes(navController = navController) {
        val code = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }
        val users = mapOf(
            "171" to "Admin",
            "222" to "Javi",
            "333" to "Maria",
            "444" to "Ander"
        )
        val authState by authViewModel.state.collectAsState()
        LaunchedEffect(authState.role) {
            if (authState.role != null) {
                navController.navigate("dashboard") {
                    popUpTo("login_screen") { inclusive = true }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7F3E3)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedField(
                value = code.value,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        code.value = input
                    }
                },
                placeholder = { Text("Ingrese número", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    validateCode(code.value, users, message, authViewModel)
                }),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp)
            )
            PrimaryButton(
                onClick = {
                    validateCode(code.value, users, message, authViewModel)
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

@Composable
fun ZonasScreen(navController: NavController) {
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
                PrimaryButton(
                    onClick = { navController.navigate("barra_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Barra", color = Color.Black)
                }
                PrimaryButton(
                    onClick = { navController.navigate("comedor_bar") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comedor Bar", color = Color.Black)
                }
                PrimaryButton(
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

private fun validateCode(
    code: String,
    users: Map<String, String>,
    message: MutableState<String>,
    authViewModel: AuthViewModel
) {
    val user = users[code]
    if (user != null) {
        message.value = "Bienvenido, $user"
        authViewModel.login(code)
    } else {
        message.value = "Código incorrecto"
    }
}

@Composable
fun NextScreen(navController: NavController) {
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
                PrimaryButton(
                    onClick = { navController.navigate("barra_screen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Barra", color = Color.Black)
                }
                PrimaryButton(
                    onClick = { navController.navigate("comedor_bar") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comedor Bar", color = Color.Black)
                }
                PrimaryButton(
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

@Composable
fun BarraScreen(navController: NavController) {
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
                PrimaryButton(
                    onClick = { navController.navigate("barra_comer_aqui") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Comer aquí", color = Color.Black)
                }
                PrimaryButton(
                    onClick = { navController.navigate("barra_para_llevar") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE9D6)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Para llevar", color = Color.Black)
                }
            }
        }
    }
}

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
                    PrimaryButton(
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

@Composable
fun BarraComerAquiMesaScreen(navController: NavController, mesaId: Int) {
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
                Text(text = "Barra $mesaId", style = MaterialTheme.typography.headlineMedium)
                ComidaYBebidaButtons(navController = navController)
            }
        }
    }
}

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

@Composable
fun IngredientesScreen(navController: NavController, nombre: String, ingredientes: String) {
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
                Text(text = nombre, style = MaterialTheme.typography.headlineMedium)
                Text(text = ingredientes, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun ComedorBarScreen(navController: NavController) {
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
                for (i in 1..8) {
                    PrimaryButton(
                        onClick = { navController.navigate("comedor_bar_mesa_$i") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Mesa $i", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun ComedorBarMesaScreen(navController: NavController, mesaId: Int) {
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
                Text(text = "Mesa $mesaId", style = MaterialTheme.typography.headlineMedium)
                ComidaYBebidaButtons(navController = navController)
            }
        }
    }
}

@Composable
fun ComedorPrincipalScreen(navController: NavController) {
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
                for (i in 1..12) {
                    PrimaryButton(
                        onClick = { navController.navigate("comedor_principal_mesa_$i") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F9F)),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Mesa $i", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun ComedorPrincipalMesaScreen(navController: NavController, mesaId: Int) {
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
                Text(text = "Mesa $mesaId", style = MaterialTheme.typography.headlineMedium)
                ComidaYBebidaButtons(navController = navController)
            }
        }
    }
}

@Composable
fun ComidaYBebidaButtons(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        PrimaryButton(
            onClick = { navController.navigate("comida_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Comida", color = Color.Black)
        }
        PrimaryButton(
            onClick = { navController.navigate("bebida_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Bebida", color = Color.Black)
        }
    }
}

// Reemplazar AnimatedNavHost y accompanist.navigation.animation.composable por NavHost y androidx.navigation.compose.composable

@Composable
fun BarraParaLlevarScreen(navController: NavController) {
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
                    text = "Barra Para Llevar",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
                ComidaYBebidaButtons(navController = navController)
            }
        }
    }
}

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
                        PrimaryButton(
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

@Composable
fun DetalleBebidaScreen(navController: NavController, nombre: String, descripcion: String) {
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
                Text(text = nombre, style = MaterialTheme.typography.headlineMedium)
                Text(text = descripcion, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
