package com.gerardo.comandas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gerardo.comandas.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController = navController) }
        composable("login_screen") { LoginScreen(navController = navController) }
        composable("zonas_screen") { ZonasScreen(navController = navController) }
        composable("barra_screen") { BarraScreen(navController = navController) }
        composable("barra_comer_aqui") { BarraComerAquiScreen(navController = navController) }
        composable("barra_para_llevar") { BarraParaLlevarScreen(navController = navController) }
        composable("comedor_bar") { ComedorBarScreen(navController = navController) }
        composable("comedor_principal") { ComedorPrincipalScreen(navController = navController) }
        composable("barra_comer_aqui_mesa_{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                BarraComerAquiMesaScreen(navController, mesaId)
            }
        }
        composable("comedor_bar_mesa_{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                ComedorBarMesaScreen(navController, mesaId)
            }
        }
        composable("comida_screen") { ComidaScreen(navController) }
        composable("ingredientes_screen/{nombre}/{ingredientes}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val ingredientes = backStackEntry.arguments?.getString("ingredientes") ?: ""
            IngredientesScreen(navController, nombre, ingredientes)
        }
        composable("bebida_screen") { BebidaScreen(navController, showButtons = false) }
        composable("detalle_bebida_screen/{nombre}/{descripcion}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
            DetalleBebidaScreen(navController, nombre, descripcion)
        }
        composable("comedor_principal_mesa_{mesaId}") { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                ComedorPrincipalMesaScreen(navController, mesaId)
            }
        }
    }
}
