package com.gerardo.comandas.printing

import com.gerardo.comandas.domain.model.Comanda

interface ReceiptBuilder {
    fun build(comanda: Comanda): String
}
