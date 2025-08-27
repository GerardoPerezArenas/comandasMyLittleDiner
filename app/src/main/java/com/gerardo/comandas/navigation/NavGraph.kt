package com.gerardo.comandas.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

object Routes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val TABLES = "tables"
    const val ORDERS = "orders"
    const val MENU = "menu"
    const val DRINKS = "drinks"
    const val SETTINGS = "settings"
    const val ADMIN = "admin"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        composable(Routes.LOGIN) { /* TODO: Login Screen */ }
        composable(Routes.DASHBOARD) { /* TODO: Dashboard Screen */ }
        composable(Routes.TABLES) { /* TODO: Tables Screen */ }
        composable(Routes.ORDERS) { /* TODO: Orders Screen */ }
        composable(Routes.MENU) { /* TODO: Menu Screen */ }
        composable(Routes.DRINKS) { /* TODO: Drinks Screen */ }
        composable(Routes.SETTINGS) { /* TODO: Settings Screen */ }
        composable(Routes.ADMIN) { /* TODO: Admin Screen */ }
    }
}

