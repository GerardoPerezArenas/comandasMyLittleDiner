package com.gerardo.comandas.di

import android.content.Context
import com.gerardo.comandas.data.repositories.MenuRepository
import com.gerardo.comandas.data.repositories.OrderRepository
import com.gerardo.comandas.data.repositories.SettingsRepository
import com.gerardo.comandas.data.repositories.TableRepository
import com.gerardo.comandas.data.repositories.impl.MenuRepositoryImpl
import com.gerardo.comandas.data.repositories.impl.OrderRepositoryImpl
import com.gerardo.comandas.data.repositories.impl.SettingsRepositoryImpl
import com.gerardo.comandas.data.repositories.impl.TableRepositoryImpl
import com.gerardo.comandas.data.room.RetroBurgerDatabase
import com.gerardo.comandas.domain.usecases.AddOrderLine
import com.gerardo.comandas.domain.usecases.CreateOrder
import com.gerardo.comandas.domain.usecases.SetTableOccupied

object AppContainer {
    
    @Volatile
    private var instance: AppContainer? = null
    
    lateinit var database: RetroBurgerDatabase
        private set
    
    lateinit var orderRepository: OrderRepository
        private set
        
    lateinit var tableRepository: TableRepository
        private set
        
    lateinit var menuRepository: MenuRepository
        private set
        
    lateinit var settingsRepository: SettingsRepository
        private set
    
    // Use cases
    lateinit var createOrder: CreateOrder
        private set
        
    lateinit var addOrderLine: AddOrderLine
        private set
        
    lateinit var setTableOccupied: SetTableOccupied
        private set
    
    fun initialize(context: Context) {
        synchronized(this) {
            database = RetroBurgerDatabase.getInstance(context)
            
            // Repositories
            orderRepository = OrderRepositoryImpl(
                orderDao = database.orderDao(),
                orderLineDao = database.orderLineDao()
            )
            
            tableRepository = TableRepositoryImpl(
                tableSpotDao = database.tableSpotDao()
            )
            
            menuRepository = MenuRepositoryImpl(
                menuItemDao = database.menuItemDao()
            )
            
            settingsRepository = SettingsRepositoryImpl(context)
            
            // Use cases
            createOrder = CreateOrder(orderRepository)
            addOrderLine = AddOrderLine(orderRepository)
            setTableOccupied = SetTableOccupied(tableRepository)
        }
    }
}