package com.example.eurofitapp

import NavigationWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.eurofitapp.ui.theme.EuroFitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuroFitAppTheme {
                NavigationWrapper()
            }
        }
    }
}