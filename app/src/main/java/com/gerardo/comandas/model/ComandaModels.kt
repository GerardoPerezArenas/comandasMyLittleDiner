package com.gerardo.comandas.model

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

// Productos de la carta
val productosCarta = listOf(
    Producto(1, "MARILYN", TipoProducto.Comida),
    Producto(2, "ELVIS", TipoProducto.Comida),
    Producto(3, "PATTY MELT", TipoProducto.Comida),
    Producto(4, "STEVE MCQUEEN", TipoProducto.Comida),
    Producto(5, "GIVE ME MORE (NOSOLOBURGER)", TipoProducto.Comida),
    Producto(6, "POLLO CRUJIENTE", TipoProducto.Comida),
    Producto(7, "BACON CHESSE FRIES", TipoProducto.Comida),
    Producto(8, "NACHOS", TipoProducto.Comida),
    Producto(9, "ALITAS BUFFALO WINGS", TipoProducto.Comida)
)

// Zonas del local
val zonas = listOf(
    Zona("Barra", 10),
    Zona("Bar", 6),
    Zona("Comedor", 8)
)
