package com.gerardo.comandas.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp

@Composable
fun RibeteCuadriculado(modifier: Modifier = Modifier, squares: Int = 20) {
    Canvas(modifier = modifier.fillMaxWidth().height(32.dp)) {
        val squareWidth = size.width / squares
        for (i in 0 until squares) {
            drawRect(
                color = if (i % 2 == 0) Color.White else Color.Black,
                topLeft = Offset(i * squareWidth, 0f),
                size = Size(squareWidth, size.height),
                style = Fill
            )
        }
    }
}
