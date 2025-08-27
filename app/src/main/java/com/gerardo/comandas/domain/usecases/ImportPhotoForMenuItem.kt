package com.gerardo.comandas.domain.usecases

import com.gerardo.comandas.data.repositories.MenuRepository

class ImportPhotoForMenuItem(private val menuRepository: MenuRepository) {
    operator fun invoke(productId: Int, imagePath: String) {
        menuRepository.importPhotoForMenuItem(productId, imagePath)
    }
}

