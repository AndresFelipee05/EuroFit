package com.example.eurofitapp.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IMCScreen(
    imc: Float,
    navigateToHome: () -> Unit // Función para navegar a HomeScreen
) {
    var showDialog by rememberSaveable { mutableStateOf(false) } // Estado para controlar el diálogo
    val imcCategory = getIMCCategory(imc) // Obtener la categoría del IMC

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))

        // Mostrar IMC y su categoría con color correspondiente
        Text(text = "Tu IMC es: ${"%.2f".format(imc)}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = imcCategory.text,
            fontSize = 20.sp,
            color = imcCategory.color
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto "¿Qué es el IMC?" en azul y clickable
        Text(
            text = "¿Qué es el IMC?",
            color = Color.Blue,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { showDialog = true } // Mostrar diálogo al hacer clic
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver a HomeScreen
        Button(
            onClick = navigateToHome // Navegar a HomeScreen
        ) {
            Text(text = "Volver a Home")
        }
    }

    // Diálogo para mostrar la explicación del IMC
    if (showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog = false }, // Cerrar diálogo
            title = { Text(text = "¿Qué es el IMC?") },
            text = {
                Text(
                    text = "El Índice de Masa Corporal (IMC) es una medida que se utiliza para evaluar si una persona tiene un peso saludable en relación con su altura. " +
                            "Se calcula dividiendo el peso en kilogramos por el cuadrado de la altura en metros (IMC = peso / altura²).\n\n" +
                            "Interpretación del IMC:\n" +
                            "- Menos de 18.5: Bajo peso\n" +
                            "- 18.5 - 24.9: Peso normal\n" +
                            "- 25.0 - 29.9: Sobrepeso\n" +
                            "- 30.0 o más: Obesidad"
                )
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}

// 🔹 Función para obtener la categoría del IMC y el color asociado
fun getIMCCategory(imc: Float): IMCCategory {
    return when {
        imc < 18.5 -> IMCCategory("Bajo peso", Color.Red)
        imc < 24.9 -> IMCCategory("Peso normal", Color.Green)
        imc < 29.9 -> IMCCategory("Sobrepeso", Color.Green)
        else -> IMCCategory("Obesidad", Color.Red)
    }
}

// 🔹 Clase de datos para manejar la categoría del IMC y su color
data class IMCCategory(val text: String, val color: Color)
