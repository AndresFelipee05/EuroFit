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
fun VelocidadMujerScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    fun calcularNotaVelocidad(edad: Int, tiempo: Float): Float {
        return when (edad) {
            14 -> when {
                tiempo <= 11f -> 10f
                tiempo <= 13.4f -> 9.5f
                tiempo <= 13.6f -> 9f
                tiempo <= 13.8f -> 8.5f
                tiempo <= 14f -> 8f
                tiempo <= 14.2f -> 7.5f
                tiempo <= 14.4f -> 7f
                tiempo <= 14.6f -> 6.5f
                tiempo <= 14.8f -> 6f
                tiempo <= 15f -> 5.5f
                tiempo <= 15.2f -> 5f
                tiempo <= 15.4f -> 4.5f
                tiempo <= 15.6f -> 4f
                tiempo <= 15.8f -> 3.5f
                tiempo <= 16f -> 3f
                tiempo <= 16.2f -> 2.5f
                else -> 2f
            }
            15 -> when {
                tiempo <= 11f -> 10f
                tiempo <= 13.1f -> 9.5f
                tiempo <= 13.3f -> 9f
                tiempo <= 13.5f -> 8.5f
                tiempo <= 13.7f -> 8f
                tiempo <= 13.9f -> 7.5f
                tiempo <= 14.1f -> 7f
                tiempo <= 14.3f -> 6.5f
                tiempo <= 14.5f -> 6f
                tiempo <= 14.7f -> 5.5f
                tiempo <= 14.9f -> 5f
                tiempo <= 15.1f -> 4.5f
                tiempo <= 15.3f -> 4f
                tiempo <= 15.5f -> 3.5f
                tiempo <= 15.7f -> 3f
                tiempo <= 15.9f -> 2.5f
                else -> 2f
            }
            else -> when {
                tiempo <= 11f -> 10f
                tiempo <= 13.1f -> 9.5f
                tiempo <= 13.3f -> 9f
                tiempo <= 13.5f -> 8.5f
                tiempo <= 13.7f -> 8f
                tiempo <= 13.9f -> 7.5f
                tiempo <= 14.1f -> 7f
                tiempo <= 14.3f -> 6.5f
                tiempo <= 14.5f -> 6f
                tiempo <= 14.7f -> 5.5f
                tiempo <= 14.9f -> 5f
                tiempo <= 15.1f -> 4.5f
                tiempo <= 15.3f -> 4f
                tiempo <= 15.5f -> 3.5f
                tiempo <= 15.7f -> 3f
                tiempo <= 15.9f -> 2.5f
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
            text = "Resultado de la Prueba de Velocidad (Mujeres)",
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
