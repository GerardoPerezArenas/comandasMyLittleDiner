package com.gerardo.comandas.sync

class SyncWorker {
    private val syncedOrders = mutableListOf<String>()

    fun onOrderStateChanged(orderId: String, newState: String) {
        if (newState == "PAID") {
            registerSync(orderId)
        }
    }

    private fun registerSync(orderId: String) {
        syncedOrders.add(orderId)
    }

    fun getSyncedOrders(): List<String> = syncedOrders
}
