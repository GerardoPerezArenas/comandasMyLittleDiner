package com.gerardo.comandas.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gerardo.comandas.MainScreen
import com.gerardo.comandas.LoginScreen
import com.gerardo.comandas.ZonasScreen
import com.gerardo.comandas.BarraScreen
import com.gerardo.comandas.BarraComerAquiScreen
import com.gerardo.comandas.BarraParaLlevarScreen
import com.gerardo.comandas.ComedorBarScreen
import com.gerardo.comandas.ComedorPrincipalScreen
import com.gerardo.comandas.BarraComerAquiMesaScreen
import com.gerardo.comandas.ComedorBarMesaScreen
import com.gerardo.comandas.ComedorPrincipalMesaScreen
import com.gerardo.comandas.ComidaScreen
import com.gerardo.comandas.IngredientesScreen
import com.gerardo.comandas.BebidaScreen
import com.gerardo.comandas.DetalleBebidaScreen
import com.gerardo.comandas.ui.AuthViewModel

object AppRoutes {
    const val MAIN_SCREEN = "main_screen"
    const val LOGIN_SCREEN = "login_screen" 
    const val DASHBOARD = "dashboard"
    const val BARRA_SCREEN = "barra_screen"
    const val BARRA_COMER_AQUI = "barra_comer_aqui"
    const val BARRA_PARA_LLEVAR = "barra_para_llevar"
    const val COMEDOR_BAR = "comedor_bar"
    const val COMEDOR_PRINCIPAL = "comedor_principal"
    const val COMIDA_SCREEN = "comida_screen"
    const val BEBIDA_SCREEN = "bebida_screen"
    
    // Routes with parameters
    const val BARRA_COMER_AQUI_MESA = "barra_comer_aqui_mesa/{mesaId}"
    const val COMEDOR_BAR_MESA = "comedor_bar_mesa/{mesaId}"
    const val COMEDOR_PRINCIPAL_MESA = "comedor_principal_mesa/{mesaId}"
    const val INGREDIENTES_SCREEN = "ingredientes_screen/{nombre}/{ingredientes}"
    const val DETALLE_BEBIDA_SCREEN = "detalle_bebida_screen/{nombre}/{descripcion}"
    
    // Navigation functions
    fun navegarAMesaBarra(mesaId: Int) = "barra_comer_aqui_mesa/$mesaId"
    fun navegarAMesaComedorBar(mesaId: Int) = "comedor_bar_mesa/$mesaId"
    fun navegarAMesaComedorPrincipal(mesaId: Int) = "comedor_principal_mesa/$mesaId"
    fun navegarAIngredientes(nombre: String, ingredientes: String) = "ingredientes_screen/$nombre/$ingredientes"
    fun navegarADetalleBebida(nombre: String, descripcion: String) = "detalle_bebida_screen/$nombre/$descripcion"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = AppRoutes.MAIN_SCREEN
) {
    val authViewModel: AuthViewModel = viewModel()
    
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(AppRoutes.MAIN_SCREEN) { 
            MainScreen(navController = navController, authViewModel = authViewModel) 
        }
        
        composable(AppRoutes.LOGIN_SCREEN) { 
            LoginScreen(navController = navController, authViewModel = authViewModel) 
        }
        
        composable(AppRoutes.DASHBOARD) { 
            ZonasScreen(navController = navController) 
        }
        
        composable(AppRoutes.BARRA_SCREEN) { 
            BarraScreen(navController = navController) 
        }
        
        composable(AppRoutes.BARRA_COMER_AQUI) { 
            BarraComerAquiScreen(navController = navController) 
        }
        
        composable(AppRoutes.BARRA_PARA_LLEVAR) { 
            BarraParaLlevarScreen(navController = navController) 
        }
        
        composable(AppRoutes.COMEDOR_BAR) { 
            ComedorBarScreen(navController = navController) 
        }
        
        composable(AppRoutes.COMEDOR_PRINCIPAL) { 
            ComedorPrincipalScreen(navController = navController) 
        }
        
        composable(AppRoutes.BARRA_COMER_AQUI_MESA) { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                BarraComerAquiMesaScreen(navController, mesaId)
            }
        }
        
        composable(AppRoutes.COMEDOR_BAR_MESA) { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                ComedorBarMesaScreen(navController, mesaId)
            }
        }
        
        composable(AppRoutes.COMEDOR_PRINCIPAL_MESA) { backStackEntry ->
            val mesaId = backStackEntry.arguments?.getString("mesaId")?.toIntOrNull()
            if (mesaId != null) {
                ComedorPrincipalMesaScreen(navController, mesaId)
            }
        }
        
        composable(AppRoutes.COMIDA_SCREEN) { 
            ComidaScreen(navController) 
        }
        
        composable(AppRoutes.INGREDIENTES_SCREEN) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val ingredientes = backStackEntry.arguments?.getString("ingredientes") ?: ""
            IngredientesScreen(navController, nombre, ingredientes)
        }
        
        composable(AppRoutes.BEBIDA_SCREEN) {
            BebidaScreen(navController, showButtons = false)
        }
        
        composable(AppRoutes.DETALLE_BEBIDA_SCREEN) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
            DetalleBebidaScreen(navController, nombre, descripcion)
        }
    }
}