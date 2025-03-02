package com.example.eurofitapp.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CooperMujerScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    fun calcularNotaCooper12(distancia: Int): Float {
        return when (distancia) {
            in 2200..Int.MAX_VALUE -> 10f
            in 2100..2199 -> 9.5f
            in 2000..2099 -> 9f
            in 1900..1999 -> 8.5f
            in 1800..1899 -> 8f
            in 1750..1799 -> 7.5f
            in 1700..1749 -> 7f
            in 1650..1699 -> 6.5f
            in 1600..1649 -> 6f
            in 1550..1599 -> 5.5f
            in 1500..1549 -> 5f
            in 1450..1499 -> 4.5f
            in 1400..1449 -> 4f
            in 1350..1399 -> 3.5f
            in 1300..1349 -> 3f
            in 1250..1299 -> 2.5f
            in 0..1249 -> 2f
            else -> 0f
        }
    }

    fun calcularNotaCooper13(distancia: Int): Float {
        return when (distancia) {
            in 2100..Int.MAX_VALUE -> 10f
            in 2050..2099 -> 9.5f
            in 2000..2049 -> 9f
            in 1950..1999 -> 8.5f
            in 1900..1949 -> 8f
            in 1850..1899 -> 7.5f
            in 1800..1849 -> 7f
            in 1750..1799 -> 6.5f
            in 1700..1749 -> 6f
            in 1650..1699 -> 5.5f
            in 1600..1649 -> 5f
            in 1550..1599 -> 4.5f
            in 1500..1549 -> 4f
            in 1450..1499 -> 3.5f
            in 1400..1449 -> 3f
            in 1350..1399 -> 2.5f
            in 0..1349 -> 2f
            else -> 0f
        }
    }

    fun calcularNotaCooper14(distancia: Int): Float {
        return when (distancia) {
            in 2200..Int.MAX_VALUE -> 10f
            in 2050..2199 -> 9.5f
            in 1900..2049 -> 9f
            in 1850..1899 -> 8.5f
            in 1800..1849 -> 8f
            in 1750..1799 -> 7.5f
            in 1700..1749 -> 7f
            in 1650..1699 -> 6.5f
            in 1600..1649 -> 6f
            in 1550..1599 -> 5.5f
            in 1500..1549 -> 5f
            in 1450..1499 -> 4.5f
            in 1400..1449 -> 4f
            in 1350..1399 -> 3.5f
            in 1300..1349 -> 3f
            in 1250..1299 -> 2.5f
            in 0..1249 -> 2f
            else -> 0f
        }
    }

    fun calcularNotaCooper15(distancia: Int): Float {
        return when (distancia) {
            in 2250..Int.MAX_VALUE -> 10f
            in 2150..2249 -> 9.5f
            in 2050..2149 -> 9f
            in 1975..2049 -> 8.5f
            in 1900..1974 -> 8f
            in 1850..1899 -> 7.5f
            in 1800..1849 -> 7f
            in 1750..1799 -> 6.5f
            in 1700..1749 -> 6f
            in 1650..1699 -> 5.5f
            in 1600..1649 -> 5f
            in 1550..1599 -> 4.5f
            in 1500..1549 -> 4f
            in 1450..1499 -> 3.5f
            in 1400..1449 -> 3f
            in 1350..1399 -> 2.5f
            in 0..1349 -> 2f
            else -> 0f
        }
    }

    fun calcularNotaCooper16(distancia: Int): Float {
        return when (distancia) {
            in 2300..Int.MAX_VALUE -> 10f
            in 2200..2299 -> 9.5f
            in 2100..2199 -> 9f
            in 2025..2099 -> 8.5f
            in 1950..2024 -> 8f
            in 1900..1949 -> 7.5f
            in 1850..1899 -> 7f
            in 1800..1849 -> 6.5f
            in 1750..1799 -> 6f
            in 1700..1749 -> 5.5f
            in 1650..1699 -> 5f
            in 1600..1649 -> 4.5f
            in 1550..1599 -> 4f
            in 1500..1549 -> 3.5f
            in 1450..1499 -> 3f
            in 1400..1449 -> 2.5f
            in 0..1399 -> 2f
            else -> 0f
        }
    }

    val calcularNota = when (userAge) {
        in Int.MIN_VALUE..12 -> ::calcularNotaCooper12
        13 -> ::calcularNotaCooper13
        14 -> ::calcularNotaCooper14
        15 -> ::calcularNotaCooper15
        else -> ::calcularNotaCooper16
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Resultado de la Prueba de Cooper (Mujeres)",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Distancia (en metros)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val distancia = inputValue.toIntOrNull()
                if (distancia != null) {
                    nota = calcularNota(distancia)
                    errorMessage = ""
                } else {
                    errorMessage = "Por favor, ingresa un número válido."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Nota")
        }

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = Color.Red, modifier = Modifier.padding(8.dp))
        } else if (nota > 0) {
            Text(
                "Tu nota es: $nota",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
