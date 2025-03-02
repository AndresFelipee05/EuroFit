package com.example.eurofitapp.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(text = "Tu IMC es: ${"%.2f".format(imc)}", fontSize = 24.sp)

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