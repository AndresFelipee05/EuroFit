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
fun AbdominalesMujerScreen(
    userAge: Int,
    userSex: String
) {
    var nota by rememberSaveable { mutableStateOf(0.0f) }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Función para obtener la nota basada en la puntuación para mujeres de 12 años
    fun calcularNotaAbdominales12(puntuacion: Int): Float {
        return when (puntuacion) {
            14 -> 2f
            15 -> 2.5f
            16 -> 3f
            17 -> 3.5f
            18 -> 4f
            19 -> 4.5f
            20 -> 5f
            21 -> 5.5f
            22 -> 6f
            23 -> 6.5f
            24 -> 7f
            25 -> 7.5f
            26 -> 8f
            27 -> 8.5f
            28 -> 9f
            29 -> 9.5f
            in 30 downTo Int.MIN_VALUE -> 10f
            else -> 0f // Puntuación no válida
        }
    }

    // Función para obtener la nota basada en la puntuación para mujeres de 13 años
    fun calcularNotaAbdominales13(puntuacion: Int): Float {
        return when (puntuacion) {
            16 -> 2f
            17 -> 2.5f
            18 -> 3f
            19 -> 3.5f
            20 -> 4f
            21 -> 4.5f
            22 -> 5f
            23 -> 5.5f
            24 -> 6f
            25 -> 6.5f
            26 -> 7f
            27 -> 7.5f
            28 -> 8f
            29 -> 8.5f
            30 -> 9f
            31 -> 9.5f
            in 32 downTo Int.MIN_VALUE -> 10f
            else -> 0f // Puntuación no válida
        }
    }

    // Función para obtener la nota basada en la puntuación para mujeres de 14 años
    fun calcularNotaAbdominales14(puntuacion: Int): Float {
        return when (puntuacion) {
            17 -> 2f
            18 -> 2.5f
            19 -> 3f
            20 -> 3.5f
            21 -> 4f
            22 -> 4.5f
            23 -> 5f
            24 -> 5.5f
            25 -> 6f
            26 -> 6.5f
            27 -> 7f
            28 -> 7.5f
            29 -> 8f
            30 -> 8.5f
            31 -> 9f
            32 -> 9.5f
            in 33 downTo Int.MIN_VALUE -> 10f
            else -> 0f // Puntuación no válida
        }
    }

    // Función para obtener la nota basada en la puntuación para mujeres de 15 años
    fun calcularNotaAbdominales15(puntuacion: Int): Float {
        return when (puntuacion) {
            19 -> 2f
            20 -> 2.5f
            21 -> 3f
            22 -> 3.5f
            23 -> 4f
            24 -> 4.5f
            25 -> 5f
            26 -> 5.5f
            27 -> 6f
            28 -> 6.5f
            29 -> 7f
            30 -> 7.5f
            31 -> 8f
            32 -> 8.5f
            33 -> 9f
            34 -> 9.5f
            in 35 downTo Int.MIN_VALUE -> 10f
            else -> 0f // Puntuación no válida
        }
    }

    // Función para obtener la nota basada en la puntuación para mujeres de 16 años o más
    fun calcularNotaAbdominales16(puntuacion: Int): Float {
        return when (puntuacion) {
            21 -> 2f
            22 -> 2.5f
            23 -> 3f
            24 -> 3.5f
            25 -> 4f
            26 -> 4.5f
            27 -> 5f
            28 -> 5.5f
            29 -> 6f
            30 -> 6.5f
            31 -> 7f
            32 -> 7.5f
            33 -> 8f
            34 -> 8.5f
            35 -> 9f
            36 -> 9.5f
            in 37 downTo Int.MIN_VALUE -> 10f
            else -> 0f // Puntuación no válida
        }
    }

    // Lógica para decidir qué función de cálculo usar según la edad
    val calcularNota = when (userAge) {
        12 -> ::calcularNotaAbdominales12
        13 -> ::calcularNotaAbdominales13
        14 -> ::calcularNotaAbdominales14
        15 -> ::calcularNotaAbdominales15
        in 16..Int.MAX_VALUE -> ::calcularNotaAbdominales16 // Para 16 años o más
        else -> { _: Int -> 0f }  // Si la edad no es válida
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Resultado de la Prueba Abdominales Mujer",
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Instrucciones para ingresar la puntuación
        Text(
            text = "Introduce el número de repeticiones realizadas en 30 segundos:",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 16.sp
        )

        // Campo de texto para ingresar el número de repeticiones
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Repeticiones") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón para calcular la nota
        Button(
            onClick = {
                val repeticiones = inputValue.toIntOrNull()
                if (repeticiones != null) {
                    // Lógica para asignar 10 automáticamente si las repeticiones son mayores o iguales al umbral
                    if ((userAge == 12 && repeticiones >= 30) || (userAge == 13 && repeticiones >= 32)
                        || (userAge == 14 && repeticiones >= 33) || (userAge == 15 && repeticiones >= 35)
                        || (userAge >= 16 && repeticiones >= 37)
                    ) {
                        // Solo asignar 10 si las repeticiones cumplen el umbral para cada edad
                        nota = 10f
                        errorMessage = ""
                    } else if (repeticiones in 14..37) {
                        // Calculamos la nota basada en las repeticiones
                        nota = calcularNota(repeticiones)
                        errorMessage = ""
                    }
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
