package com.gerardo.comandas.data.repository

import com.gerardo.comandas.domain.model.Producto

interface ComandaRepository {
    fun getProductos(): List<Producto>
}
