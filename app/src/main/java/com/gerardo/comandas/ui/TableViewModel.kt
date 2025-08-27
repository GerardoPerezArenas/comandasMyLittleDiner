package com.gerardo.comandas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardo.comandas.data.repository.TableRepository
import com.gerardo.comandas.data.room.TableSpot
import com.gerardo.comandas.data.room.Zone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TableViewModel(private val tableRepository: TableRepository) : ViewModel() {
    private val _zones = MutableStateFlow<List<Zone>>(emptyList())
    val zones: StateFlow<List<Zone>> = _zones

    private val _tables = MutableStateFlow<List<TableSpot>>(emptyList())
    val tables: StateFlow<List<TableSpot>> = _tables

    fun loadZones() {
        viewModelScope.launch {
            _zones.value = tableRepository.getZones()
        }
    }

    fun loadTables(zoneId: Int) {
        viewModelScope.launch {
            _tables.value = tableRepository.getTablesByZone(zoneId)
        }
    }

    fun changeTableState(tableId: Int, state: String) {
        viewModelScope.launch {
            tableRepository.changeTableState(tableId, state)
            // Recargar mesas si es necesario
        }
    }
}

