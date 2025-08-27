package com.gerardo.comandas.data.repositories

import com.gerardo.comandas.model.Producto

interface MenuRepository {
    fun getMenu(): List<Producto>
    fun importPhotoForMenuItem(productId: Int, imagePath: String)
}

