package com.gerardo.comandas.data.repositories

import com.gerardo.comandas.domain.model.Producto
import com.gerardo.comandas.domain.model.TipoProducto

class MenuRepositoryImpl : MenuRepository {
    override fun getMenu(): List<Producto> {
        return listOf(
            Producto(id = 1, nombre = "Hamburguesa", tipo = TipoProducto.Comida),
            Producto(id = 2, nombre = "Pizza", tipo = TipoProducto.Comida),
            Producto(id = 3, nombre = "Ensalada", tipo = TipoProducto.Comida),
            Producto(id = 4, nombre = "Refresco", tipo = TipoProducto.Bebida)
        )
    }

    override fun importPhotoForMenuItem(productId: Int, imagePath: String) {
        // Implementación pendiente
    }
}
