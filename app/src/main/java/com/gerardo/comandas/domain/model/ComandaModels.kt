package com.gerardo.comandas.domain.model

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
