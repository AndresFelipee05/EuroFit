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
fun LanzarMujerScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    fun calcularNotaLanzamiento(edad: Int, distancia: Float): Float {
        return when {
            edad == 15 -> when {
                distancia >= 8.5f -> 10f
                distancia >= 8.2f -> 9.5f
                distancia >= 7.9f -> 9f
                distancia >= 7.6f -> 8.5f
                distancia >= 7.3f -> 8f
                distancia >= 7.0f -> 7.5f
                distancia >= 6.7f -> 7f
                distancia >= 6.4f -> 6.5f
                distancia >= 6.1f -> 6f
                distancia >= 5.8f -> 5.5f
                distancia >= 5.5f -> 5f
                distancia >= 5.2f -> 4.5f
                distancia >= 5.0f -> 4f
                distancia >= 4.7f -> 3.5f
                distancia >= 4.4f -> 3f
                distancia >= 4.1f -> 2.5f
                distancia <= 3.8f -> 2f
                else -> 2f
            }

            edad >= 16 -> when {
                distancia >= 8.7f -> 10f
                distancia >= 8.4f -> 9.5f
                distancia >= 8.1f -> 9f
                distancia >= 7.8f -> 8.5f
                distancia >= 7.5f -> 8f
                distancia >= 7.2f -> 7.5f
                distancia >= 6.9f -> 7f
                distancia >= 6.6f -> 6.5f
                distancia >= 6.3f -> 6f
                distancia >= 6.1f -> 5.5f
                distancia >= 5.9f -> 5f
                distancia >= 5.7f -> 4.5f
                distancia >= 5.5f -> 4f
                distancia >= 5.3f -> 3.5f
                distancia >= 5.1f -> 3f
                distancia >= 4.9f -> 2.5f
                distancia <= 4.7f -> 2f
                else -> 2f
            }
            else -> 2f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultado de la Prueba de Lanzamiento (Mujeres)",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Introduce la distancia en metros:",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )

        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Distancia (metros)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val distancia = inputValue.toFloatOrNull()
                if (distancia != null) {
                    nota = calcularNotaLanzamiento(userAge, distancia)
                    errorMessage = ""
                } else {
                    errorMessage = "Por favor, ingresa un número válido."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Nota")
        }

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
