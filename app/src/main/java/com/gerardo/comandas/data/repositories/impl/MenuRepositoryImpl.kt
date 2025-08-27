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
            // Convert MenuItem entities to Producto model objects
            menuItems.map { menuItem ->
                Producto(
                    id = menuItem.id,
                    nombre = menuItem.name,
                    // For now, assume all items are food. In a real implementation,
                    // you would determine this based on categoryId or add a type field
                    tipo = TipoProducto.Comida
                )
            }
        }
    }
    
    override fun importPhotoForMenuItem(productId: Int, imagePath: String) {
        // TODO: Implement photo import functionality
        // This might involve updating a MenuItem entity with an image path
        // or storing the image in a separate table
        println("Photo imported for product $productId from path: $imagePath")
    }
}