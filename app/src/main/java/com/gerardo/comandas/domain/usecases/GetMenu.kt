package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.MenuRepository
import com.gerardo.comandas.domain.model.Producto

class GetMenu(private val menuRepository: MenuRepository) {
    operator fun invoke(): List<Producto> = menuRepository.getMenu()
}
