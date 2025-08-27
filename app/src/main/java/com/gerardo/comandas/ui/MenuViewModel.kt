package com.gerardo.comandas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardo.comandas.data.repository.MenuRepository
import com.gerardo.comandas.data.room.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    fun loadMenuItems() {
        viewModelScope.launch {
            _menuItems.value = menuRepository.getMenu()
        }
    }
}
