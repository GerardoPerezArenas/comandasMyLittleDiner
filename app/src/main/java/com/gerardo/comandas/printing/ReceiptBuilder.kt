package com.gerardo.comandas.printing

import com.gerardo.comandas.model.Comanda

interface ReceiptBuilder {
    fun build(comanda: Comanda): String
}
