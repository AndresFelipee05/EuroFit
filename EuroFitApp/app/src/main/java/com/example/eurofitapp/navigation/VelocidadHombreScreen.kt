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
fun VelocidadHombreScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    fun calcularNotaVelocidad(edad: Int, tiempo: Float): Float {
        return when (edad) {
            14 -> when {
                tiempo <= 11f -> 10f
                tiempo <= 12.6f -> 9.5f
                tiempo <= 12.8f -> 9f
                tiempo <= 13f -> 8.5f
                tiempo <= 13.2f -> 8f
                tiempo <= 13.4f -> 7.5f
                tiempo <= 13.6f -> 7f
                tiempo <= 13.8f -> 6.5f
                tiempo <= 14f -> 6f
                tiempo <= 14.2f -> 5.5f
                tiempo <= 14.4f -> 5f
                tiempo <= 14.6f -> 4.5f
                tiempo <= 15f -> 4f
                tiempo <= 15.2f -> 3.5f
                tiempo <= 15.4f -> 3f
                tiempo <= 15.6f -> 2.5f
                else -> 2f
            }
            15 -> when {
                tiempo <= 11f -> 10f
                tiempo <= 12.3f -> 9.5f
                tiempo <= 12.5f -> 9f
                tiempo <= 12.7f -> 8.5f
                tiempo <= 12.9f -> 8f
                tiempo <= 13.1f -> 7.5f
                tiempo <= 13.3f -> 7f
                tiempo <= 13.5f -> 6.5f
                tiempo <= 13.7f -> 6f
                tiempo <= 13.9f -> 5.5f
                tiempo <= 14.1f -> 5f
                tiempo <= 14.3f -> 4.5f
                tiempo <= 14.5f -> 4f
                tiempo <= 14.7f -> 3.5f
                tiempo <= 14.9f -> 3f
                tiempo <= 15.1f -> 2.5f
                else -> 2f
            }
            else -> when {
                tiempo <= 11f -> 10f
                tiempo <= 12f -> 9.5f
                tiempo <= 12.2f -> 9f
                tiempo <= 12.4f -> 8.5f
                tiempo <= 12.6f -> 8f
                tiempo <= 12.8f -> 7.5f
                tiempo <= 13f -> 7f
                tiempo <= 13.2f -> 6.5f
                tiempo <= 13.4f -> 6f
                tiempo <= 13.6f -> 5.5f
                tiempo <= 13.8f -> 5f
                tiempo <= 14f -> 4.5f
                tiempo <= 14.2f -> 4f
                tiempo <= 14.4f -> 3.5f
                tiempo <= 14.6f -> 3f
                tiempo <= 14.8f -> 2.5f
                else -> 2f
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultado de la Prueba de Velocidad (Hombres)",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Introduce el tiempo en segundos:",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )

        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Tiempo (segundos)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val tiempo = inputValue.toFloatOrNull()
                if (tiempo != null) {
                    nota = calcularNotaVelocidad(userAge, tiempo)
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
