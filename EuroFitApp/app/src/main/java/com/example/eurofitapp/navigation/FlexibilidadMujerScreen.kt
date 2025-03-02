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
fun FlexibilidadMujerScreen(
    userAge: Int,
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Función para calcular la nota según la puntuación de la flexibilidad de mujeres de 12 años o menos
    fun calcularNotaFlexibilidad12(puntuacion: Int): Float {
        return when (puntuacion) {
            in 9..Int.MAX_VALUE -> 10f
            7, 8 -> 9.5f
            5, 6 -> 9f
            3, 4 -> 8.5f
            1, 2 -> 8f
            0 -> 7.5f
            -1 -> 7f
            -2, -3 -> 6.5f
            -4, -5 -> 6f
            -6, -7 -> 5.5f
            -8, -9 -> 5f
            -10, -11 -> 4.5f
            -12, -13 -> 4f
            -14, -15 -> 3.5f
            -16, -17 -> 3f
            -18, -19 -> 2.5f
            in -20 downTo Int.MIN_VALUE -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la puntuación de la flexibilidad de mujeres de 13 años
    fun calcularNotaFlexibilidad13(puntuacion: Int): Float {
        return when (puntuacion) {
            in 12..Int.MAX_VALUE -> 10f
            10, 11 -> 9.5f
            8, 9 -> 9f
            6, 7 -> 8.5f
            4, 5 -> 8f
            2, 3 -> 7.5f
            1 -> 7f
            0 -> 6.5f
            -1 -> 6f
            -2, -3 -> 5.5f
            -4, -5 -> 5f
            -6, -7 -> 4.5f
            -8, -9 -> 4f
            -10, -11 -> 3.5f
            -12, -13 -> 3f
            in -14 downTo Int.MIN_VALUE -> 2.5f
            else -> 0f
        }
    }

    // Función para calcular la nota según la puntuación de la flexibilidad de mujeres de 14 años
    fun calcularNotaFlexibilidad14(puntuacion: Int): Float {
        return when (puntuacion) {
            in 15..Int.MAX_VALUE -> 10f
            13, 14 -> 9.5f
            11, 12 -> 9f
            9, 10 -> 8.5f
            7, 8 -> 8f
            5, 6 -> 7.5f
            4 -> 7f
            3 -> 6.5f
            2 -> 6f
            1 -> 5.5f
            0 -> 5f
            -2, -1 -> 4.5f
            -4, -3 -> 4f
            -6, -5 -> 3.5f
            -7 -> 3f
            -8 -> 2.5f
            in -9 downTo Int.MIN_VALUE -> 2f
            else -> 0f
        }
    }

    // Función para calcular la nota según la puntuación de la flexibilidad de mujeres de 15 años o más
    fun calcularNotaFlexibilidad15(puntuacion: Int): Float {
        return when (puntuacion) {
            in 17..Int.MAX_VALUE -> 10f
            15, 16 -> 9.5f
            13, 14 -> 9f
            11, 12 -> 8.5f
            9, 10 -> 8f
            7, 8 -> 7.5f
            5, 6 -> 7f
            4 -> 6.5f
            3 -> 6f
            2 -> 5.5f
            1 -> 5f
            0 -> 4.5f
            -2, -1 -> 4f
            -4, -3 -> 3.5f
            -6, -5 -> 3f
            -7 -> 2.5f
            in -8 downTo Int.MIN_VALUE -> 2f
            else -> 0f
        }
    }

    // Lógica para decidir qué función de cálculo usar según la edad
    val calcularNota = when (userAge) {
        in Int.MIN_VALUE..12 -> ::calcularNotaFlexibilidad12
        13 -> ::calcularNotaFlexibilidad13
        14 -> ::calcularNotaFlexibilidad14
        else -> ::calcularNotaFlexibilidad15
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Resultado de la Prueba de Flexibilidad",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Instrucciones para ingresar la puntuación
        Text(
            text = "Introduce la puntuación obtenida en la prueba de flexibilidad:",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )

        // Campo de texto para ingresar el número de repeticiones
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Puntuación") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón para calcular la nota
        Button(
            onClick = {
                val puntuacion = inputValue.toIntOrNull()
                if (puntuacion != null) {
                    // Lógica para asignar 10 automáticamente si la puntuación es mayor o igual a la puntuación máxima de esa edad
                    val notaCalculada = calcularNota(puntuacion)
                    if (puntuacion >= when (userAge) {
                            12 -> 9
                            13 -> 12
                            14 -> 15
                            else -> 17
                        }
                    ) {
                        // Asignar 10 si la puntuación supera el umbral de esa edad
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
