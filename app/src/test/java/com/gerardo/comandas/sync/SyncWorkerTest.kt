package com.gerardo.comandas.sync

import org.junit.Assert.assertEquals
import org.junit.Test

class SyncWorkerTest {
    @Test
    fun registersSyncWhenStatePaid() {
        val worker = SyncWorker()
        worker.onOrderStateChanged("1", "PAID")
        assertEquals(listOf("1"), worker.getSyncedOrders())
    }

    @Test
    fun doesNotRegisterWhenNotPaid() {
        val worker = SyncWorker()
        worker.onOrderStateChanged("1", "OPEN")
        assertEquals(emptyList<String>(), worker.getSyncedOrders())
    }
}
