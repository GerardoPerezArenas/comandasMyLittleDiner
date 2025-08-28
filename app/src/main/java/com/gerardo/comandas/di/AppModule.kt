package com.gerardo.comandas.di

import android.content.Context
import com.gerardo.comandas.data.repository.ComandaRepository
import com.gerardo.comandas.data.repository.ComandaRepositoryImpl
import com.gerardo.comandas.data.repository.TableRepository
import com.gerardo.comandas.data.repository.TableRepositoryImpl
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.domain.usecase.GetProductosUseCase

object AppModule {
    private val repository: ComandaRepository by lazy { ComandaRepositoryImpl() }
    val getProductosUseCase: GetProductosUseCase by lazy { GetProductosUseCase(repository) }
    
    fun provideTableRepository(context: Context): TableRepository {
        val db = RetroBurgerDatabase.getDatabase(context)
        return TableRepositoryImpl(db.zoneDao(), db.tableSpotDao())
    }
}
