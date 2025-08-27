package com.gerardo.comandas.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val name: String,
    val priceCents: Int,
    val photoPath: String? = null,
    val isDrink: Boolean = false,
    val active: Boolean = true,
    val description: String? = null
)
