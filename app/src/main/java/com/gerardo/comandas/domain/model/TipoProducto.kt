package com.gerardo.comandas.domain.model

sealed class TipoProducto {
    object Bebida : TipoProducto()
    object Comida : TipoProducto()
}

