package com.example.eurofitapp.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel : ViewModel() {
    private val _isDarkMode = MutableStateFlow(false)  // Esto es un StateFlow
    val isDarkMode: StateFlow<Boolean> = _isDarkMode  // Exponerlo como StateFlow

    fun setDarkMode(isDark: Boolean) {
        _isDarkMode.value = isDark
    }

    fun toggleTheme() {
        _isDarkMode.value = !_isDarkMode.value
    }
}



