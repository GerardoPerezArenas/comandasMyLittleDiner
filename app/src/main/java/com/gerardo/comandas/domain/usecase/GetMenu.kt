package com.gerardo.comandas.domain.usecase

import com.gerardo.comandas.data.repository.MenuRepository
import com.gerardo.comandas.data.room.MenuItem

class GetMenu(private val menuRepository: MenuRepository) {
    suspend operator fun invoke(): List<MenuItem> = menuRepository.getMenu()
}

