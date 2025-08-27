package com.gerardo.comandas.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(
    entities = [Category::class, MenuItem::class, Zone::class, TableSpot::class, Order::class, OrderLine::class],
    version = 1
)
abstract class RetroBurgerDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun zoneDao(): ZoneDao
    abstract fun tableSpotDao(): TableSpotDao
    abstract fun orderDao(): OrderDao
    abstract fun orderLineDao(): OrderLineDao

    companion object {
        @Volatile
        private var INSTANCE: RetroBurgerDatabase? = null

        fun getInstance(context: Context): RetroBurgerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RetroBurgerDatabase::class.java,
                    "retro_burger.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            INSTANCE?.let { database ->
                                database.zoneDao().insertAll(SeedData.zones)
                                database.tableSpotDao().insertAll(SeedData.tableSpots)
                                database.categoryDao().insertAll(SeedData.categories)
                                database.menuItemDao().insertAll(SeedData.menuItems)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
