package com.gerardo.comandas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gerardo.comandas.ui.AuthViewModel
import com.gerardo.comandas.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("main_screen") { MainScreen(navController = navController) }
        composable("login_screen") { LoginScreen(navController = navController, authViewModel = authViewModel) }
        composable("zonas_screen?name={name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ZonasScreen(navController = navController, name = name)
        }
        composable("barra_screen") { BarraScreen(navController = navController) }
        composable("barra_comer_aqui") { BarraComerAquiScreen(navController = navController) }
        composable("barra_para_llevar") { BarraParaLlevarScreen(navController = navController) }
        composable("barra_comer_aqui_mesa/{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")!!.toInt()
            BarraComerAquiMesaScreen(navController = navController, mesaId = mesaId)
        }
        composable("comedor_bar") { ComedorBarScreen(navController = navController) }
        composable("comedor_bar_mesa/{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")!!.toInt()
            ComedorBarMesaScreen(navController = navController, mesaId = mesaId)
        }
        composable("comedor_principal") { ComedorPrincipalScreen(navController = navController) }
        composable("comedor_principal_mesa/{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")!!.toInt()
            ComedorPrincipalMesaScreen(navController = navController, mesaId = mesaId)
        }
        composable("comida_y_bebida_screen?mesaId={mesaId}&orderId={orderId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull() ?: 0
            val orderId = backStackEntry.arguments?.getString("orderId")?.toIntOrNull()
            ComidaYBebidaScreen(navController = navController, mesaId = mesaId, orderId = orderId)
        }
        composable("comida_screen?mesaId={mesaId}&orderId={orderId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull() ?: 0
            val orderId = backStackEntry.arguments?.getString("orderId")?.toIntOrNull()
            ComidaScreen(navController = navController, mesaId = mesaId, orderId = orderId)
        }
        composable("bebida_screen?mesaId={mesaId}&orderId={orderId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull() ?: 0
            val orderId = backStackEntry.arguments?.getString("orderId")?.toIntOrNull()
            BebidaScreen(navController = navController, mesaId = mesaId, orderId = orderId)
        }
        composable("detalle_bebida_screen/{nombre}/{descripcion}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
            DetalleBebidaScreen(navController = navController, nombre = nombre, descripcion = descripcion)
        }
        composable("ingredientes_screen/{nombre}/{ingredientes}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val ingredientes = backStackEntry.arguments?.getString("ingredientes") ?: ""
            IngredientesScreen(navController = navController, nombre = nombre, ingredientes = ingredientes)
        }
        composable("testOrder") {
            val context = androidx.compose.ui.platform.LocalContext.current
            val provider = com.gerardo.comandas.ui.TestOrderProvider(context)
            com.gerardo.comandas.ui.TestOrderScreen(
                createOrder = provider.createOrder,
                addOrderLine = provider.addOrderLine,
                getOrders = { provider.getOrders(it) },
                getOrderLines = { provider.getOrderLines(it) }
            )
        }
    }
}
