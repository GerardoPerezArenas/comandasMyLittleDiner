package com.gerardo.comandas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.gerardo.comandas.navigation.NavGraph
import com.gerardo.comandas.ui.theme.COMANDASTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            COMANDASTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
