package com.gerardo.comandas.domain.model

sealed class TipoProducto {
    object Bebida : TipoProducto()
    object Comida : TipoProducto()
}

// Modelo de producto
data class Producto(
    val id: Int,
    val nombre: String,
    val tipo: TipoProducto
)

// Modelo de comanda
data class Comanda(
    val productos: List<Producto>
)

// Modelo de zona
data class Zona(
    val nombre: String,
    val cantidadMesas: Int
)

// Modelo de mesa
data class Mesa(
    val numero: Int,
    val zona: Zona
)
