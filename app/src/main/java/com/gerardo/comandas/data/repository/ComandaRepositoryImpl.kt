package com.gerardo.comandas.data.repository

import com.gerardo.comandas.domain.model.Producto
import com.gerardo.comandas.domain.model.TipoProducto

class ComandaRepositoryImpl : ComandaRepository {
    override fun getProductos(): List<Producto> = listOf(
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
}
