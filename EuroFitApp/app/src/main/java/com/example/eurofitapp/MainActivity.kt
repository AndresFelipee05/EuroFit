package com.example.eurofitapp

import NavigationWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.eurofitapp.navigation.ThemeViewModel
import com.example.eurofitapp.ui.theme.EuroFitAppTheme

class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            EuroFitAppTheme(darkTheme = isDarkMode) {
                NavigationWrapper(
                    themeViewModel = themeViewModel // Pasamos el ViewModel a las pantallas
                )
            }
        }
    }
}

