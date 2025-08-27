package com.gerardo.comandas.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Zone::class, TableSpot::class, Category::class, MenuItem::class, Order::class, OrderLine::class],
    version = 1
)
abstract class RetroBurgerDatabase : RoomDatabase() {
    abstract fun zoneDao(): ZoneDao
    abstract fun tableSpotDao(): TableSpotDao
    abstract fun categoryDao(): CategoryDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun orderDao(): OrderDao
    abstract fun orderLineDao(): OrderLineDao

    companion object {
        @Volatile
        private var INSTANCE: RetroBurgerDatabase? = null

        fun getDatabase(context: Context): RetroBurgerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RetroBurgerDatabase::class.java,
                    "retroburger_db"
                ).addCallback(DatabaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                val database = INSTANCE
                database?.let {
                    it.zoneDao().insertAll(SeedData.zones)
                    it.tableSpotDao().insertAll(SeedData.tableSpots)
                    it.categoryDao().insertAll(SeedData.categories)
                    it.menuItemDao().insertAll(SeedData.menuItems)
                }
            }
        }
    }
}
