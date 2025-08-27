package com.gerardo.comandas.data.room

object SeedData {
    val categories = listOf(
        Category(1, "Plato"),
        Category(2, "Bebida")
    )

    val menuItems = listOf(
        MenuItem(1, 1, "MARILYN", 950, null, false, true, "Hamburguesa clásica"),
        MenuItem(2, 1, "ELVIS", 1050, null, false, true, "Hamburguesa con bacon y queso"),
        MenuItem(3, 1, "PATTY MELT", 1100, null, false, true, "Hamburguesa con pan de molde"),
        MenuItem(4, 1, "STEVE MCQUEEN", 1200, null, false, true, "Hamburguesa especial de la casa"),
        MenuItem(5, 1, "GIVE ME MORE (NOSOLOBURGER)", 1300, null, false, true, "Hamburguesa doble carne"),
        MenuItem(6, 1, "POLLO CRUJIENTE", 900, null, false, true, "Pollo empanado crujiente"),
        MenuItem(7, 1, "BACON CHESSE FRIES", 700, null, false, true, "Patatas con bacon y queso"),
        MenuItem(8, 1, "NACHOS", 800, null, false, true, "Nachos con queso y jalapeños"),
        MenuItem(9, 1, "ALITAS BUFFALO WINGS", 850, null, false, true, "Alitas de pollo picantes"),
        MenuItem(10, 2, "COLA", 250, null, true, true, "Refresco de cola"),
        MenuItem(11, 2, "AGUA", 200, null, true, true, "Agua mineral")
    )

    val zones = listOf(
        Zone(1, "Barra", "#FFB300"),
        Zone(2, "Bar", "#1976D2"),
        Zone(3, "Comedor", "#388E3C")
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
                tables.add(TableSpot(id++, zone.id, "Mesa $number", number, "FREE"))
            }
        }
        tables
    }
}
