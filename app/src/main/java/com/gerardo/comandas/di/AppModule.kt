package com.gerardo.comandas.di

import com.gerardo.comandas.data.repository.ComandaRepository
import com.gerardo.comandas.data.repository.ComandaRepositoryImpl
import com.gerardo.comandas.domain.usecase.GetProductosUseCase

object AppModule {
    private val repository: ComandaRepository by lazy { ComandaRepositoryImpl() }
    val getProductosUseCase: GetProductosUseCase by lazy { GetProductosUseCase(repository) }
}
