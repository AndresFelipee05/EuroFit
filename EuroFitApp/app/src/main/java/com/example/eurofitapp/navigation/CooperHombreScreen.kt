package com.example.eurofitapp.screens

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
fun CooperHombreScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Función para calcular la nota según la distancia recorrida para hombres de 12 años o menos
    fun calcularNotaCooper12(distancia: Int): Float {
        return when (distancia) {
            in 2600..Int.MAX_VALUE -> 10f
            in 2400..2599 -> 9.5f
            in 2200..2399 -> 9f
            in 2100..2199 -> 8.5f
            in 2000..2099 -> 8f
            in 1950..1999 -> 7.5f
            in 1900..1949 -> 7f
            in 1850..1899 -> 6.5f
            in 1800..1849 -> 6f
            in 1750..1799 -> 5.5f
            in 1700..1749 -> 5f
            in 1650..1699 -> 4.5f
            in 1600..1649 -> 4f
            in 1550..1599 -> 3.5f
            in 1500..1549 -> 3f
            in 1450..1499 -> 2.5f
            in 0..1449 -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la distancia recorrida para hombres de 13 años
    fun calcularNotaCooper13(distancia: Int): Float {
        return when (distancia) {
            in 2650..Int.MAX_VALUE -> 10f
            in 2475..2649 -> 9.5f
            in 2300..2474 -> 9f
            in 2200..2299 -> 8.5f
            in 2100..2199 -> 8f
            in 2050..2099 -> 7.5f
            in 2000..2049 -> 7f
            in 1950..1999 -> 6.5f
            in 1900..1949 -> 6f
            in 1850..1899 -> 5.5f
            in 1800..1849 -> 5f
            in 1750..1799 -> 4.5f
            in 1700..1749 -> 4f
            in 1650..1699 -> 3.5f
            in 1600..1649 -> 3f
            in 1550..1599 -> 2.5f
            in 0..1549 -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la distancia recorrida para hombres de 14 años
    fun calcularNotaCooper14(distancia: Int): Float {
        return when (distancia) {
            in 2650..Int.MAX_VALUE -> 10f
            in 2525..2649 -> 9.5f
            in 2400..2524 -> 9f
            in 2300..2399 -> 8.5f
            in 2200..2299 -> 8f
            in 2150..2199 -> 7.5f
            in 2100..2149 -> 7f
            in 2050..2099 -> 6.5f
            in 2000..2049 -> 6f
            in 1950..1999 -> 5.5f
            in 1900..1949 -> 5f
            in 1850..1899 -> 4.5f
            in 1800..1849 -> 4f
            in 1750..1799 -> 3.5f
            in 1700..1749 -> 3f
            in 1650..1699 -> 2.5f
            in 0..1649 -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la distancia recorrida para hombres de 15 años
    fun calcularNotaCooper15(distancia: Int): Float {
        return when (distancia) {
            in 2750..Int.MAX_VALUE -> 10f
            in 2625..2749 -> 9.5f
            in 2500..2624 -> 9f
            in 2400..2499 -> 8.5f
            in 2300..2399 -> 8f
            in 2250..2299 -> 7.5f
            in 2200..2249 -> 7f
            in 2150..2199 -> 6.5f
            in 2100..2149 -> 6f
            in 2050..2099 -> 5.5f
            in 2000..2049 -> 5f
            in 1950..1999 -> 4.5f
            in 1900..1949 -> 4f
            in 1850..1899 -> 3.5f
            in 1800..1849 -> 3f
            in 1750..1799 -> 2.5f
            in 0..1749 -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la distancia recorrida para hombres de 16 años o más
    fun calcularNotaCooper16(distancia: Int): Float {
        return when (distancia) {
            in 2800..Int.MAX_VALUE -> 10f
            in 2650..2799 -> 9.5f
            in 2500..2649 -> 9f
            in 2400..2499 -> 8.5f
            in 2300..2399 -> 8f
            in 2250..2299 -> 7.5f
            in 2200..2249 -> 7f
            in 2150..2199 -> 6.5f
            in 2100..2149 -> 6f
            in 2050..2099 -> 5.5f
            in 2000..2049 -> 5f
            in 1950..1999 -> 4.5f
            in 1900..1949 -> 4f
            in 1850..1899 -> 3.5f
            in 1800..1849 -> 3f
            in 1750..1799 -> 2.5f
            in 0..1749 -> 2f
            else -> 0f
        }
    }

    // Lógica para decidir qué función de cálculo usar según la edad
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
        // Título
        Text(
            text = "Resultado de la Prueba de Cooper (Hombres)",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Instrucciones para ingresar la distancia
        Text(
            text = "Introduce la distancia recorrida en la prueba de Cooper (en metros):",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )

        // Campo de texto para ingresar la distancia recorrida
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Distancia (en metros)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón para calcular la nota
        Button(
            onClick = {
                val distancia = inputValue.toIntOrNull()
                if (distancia != null) {
                    // Lógica para asignar 10 automáticamente si la distancia supera el umbral de esa edad
                    val notaCalculada = calcularNota(distancia)
                    if (distancia >= when (userAge) {
                            12 -> 2600
                            13 -> 2650
                            14 -> 2650
                            15 -> 2750
                            else -> 2800
                        }
                    ) {
                        // Asignar 10 si la distancia supera el umbral de esa edad
                        nota = 10f
                    } else {
                        nota = notaCalculada
                    }
                    errorMessage = ""
                } else {
                    errorMessage = "Por favor, ingresa un número válido."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Nota")
        }

        // Mostrar el mensaje de error o la nota calculada
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        } else if (nota > 0) {
            Text(
                text = "Tu nota es: $nota",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp),
                color = Color.Green
            )
        }
    }
}
