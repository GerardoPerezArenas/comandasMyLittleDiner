package com.gerardo.comandas.data.repositories.impl

import com.gerardo.comandas.data.repositories.MenuRepository
import com.gerardo.comandas.data.room.MenuItemDao
import com.gerardo.comandas.model.Producto
import com.gerardo.comandas.model.TipoProducto
import kotlinx.coroutines.runBlocking

class MenuRepositoryImpl(
    private val menuItemDao: MenuItemDao
) : MenuRepository {
    
    override fun getMenu(): List<Producto> {
        return runBlocking {
            val menuItems = menuItemDao.getAll()
            menuItems.map { menuItem ->
                Producto(
                    id = menuItem.id,
                    nombre = menuItem.name,
                    tipo = TipoProducto.Comida // For now, assume all items are food
                )
            }
        }
    }
    
    override fun importPhotoForMenuItem(productId: Int, imagePath: String) {
        // This would implement photo import functionality
        // For now, we'll just log as it's a stub
        println("Importing photo for product $productId from $imagePath")
    }
}