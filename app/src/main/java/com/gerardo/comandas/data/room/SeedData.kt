package com.gerardo.comandas.data.room

object SeedData {
    val categories = listOf(
        Category(1, "Plato"),
        Category(2, "Bebida")
    )

    val menuItems = listOf(
        MenuItem(1, "MARILYN", 0.0, 1),
        MenuItem(2, "ELVIS", 0.0, 1),
        MenuItem(3, "PATTY MELT", 0.0, 1),
        MenuItem(4, "STEVE MCQUEEN", 0.0, 1),
        MenuItem(5, "GIVE ME MORE (NOSOLOBURGER)", 0.0, 1),
        MenuItem(6, "POLLO CRUJIENTE", 0.0, 1),
        MenuItem(7, "BACON CHESSE FRIES", 0.0, 1),
        MenuItem(8, "NACHOS", 0.0, 1),
        MenuItem(9, "ALITAS BUFFALO WINGS", 0.0, 1),
        MenuItem(10, "COLA", 0.0, 2),
        MenuItem(11, "AGUA", 0.0, 2)
    )

    val zones = listOf(
        Zone(1, "Barra"),
        Zone(2, "Bar"),
        Zone(3, "Comedor")
    )

    val tableSpots: List<TableSpot> = run {
        val tables = mutableListOf<TableSpot>()
        var id = 1
        zones.forEach { zone ->
            val count = when (zone.id) {
                1 -> 10
                2 -> 6
                3 -> 8
                else -> 0
            }
            for (number in 1..count) {
                tables.add(TableSpot(id++, number, zone.id, isOccupied = false, isPaid = false))
            }
        }
        tables
    }
}
