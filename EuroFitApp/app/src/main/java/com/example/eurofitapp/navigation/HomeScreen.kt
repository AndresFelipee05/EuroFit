import androidx.compose.foundation.background
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToPruebas: (Int, String) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToIMC: (Float) -> Unit,
    isDarkMode: Boolean,  // Se recibe el estado del modo oscuro
    onDarkModeToggle: (Boolean) -> Unit // Función para actualizar el estado
) {
    var age by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var sex by rememberSaveable { mutableStateOf("") }

    var expanded by rememberSaveable { mutableStateOf(false) }
    val sexOptions = listOf("Hombre", "Mujer")

    val isIMCButtonEnabled = weight.isNotEmpty() && height.isNotEmpty() &&
            weight.toFloatOrNull() != null && height.toFloatOrNull() != null &&
            weight.toFloatOrNull()!! > 0 && height.toFloatOrNull()!! > 0

    val isPruebasButtonEnabled = age.isNotEmpty() && age.toIntOrNull() != null &&
            age.toIntOrNull()!! > 0 && sex.isNotEmpty()

    // Definir colores según el estado de isDarkMode
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    // Aplicar el tema basado en el modo oscuro
    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)  // Aplicar el color de fondo
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "INTRODUCE DATOS", fontSize = 25.sp, color = textColor) // Color de texto
                Spacer(modifier = Modifier.weight(1f))

                // Switch para cambiar el modo oscuro
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { onDarkModeToggle(it) } // Llamamos a la función para actualizar el estado
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { newValue -> age = newValue.filter { it.isDigit() } },
                label = { Text("Edad", color = textColor) },
                placeholder = { Text("Introduce tu edad", color = textColor) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { newValue ->
                    weight = newValue.filter { it.isDigit() || it == '.' || it == ',' }
                        .replace(',', '.')
                        .let { filtered ->
                            if (filtered.count { it == '.' } > 1) filtered.substring(0, filtered.lastIndexOf('.') + 1)
                            else filtered
                        }
                },
                label = { Text("Peso", color = textColor) },
                placeholder = { Text("Introduce tu peso (ej. 70.5)", color = textColor) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { newValue ->
                    height = newValue.filter { it.isDigit() || it == '.' || it == ',' }
                        .replace(',', '.')
                        .let { filtered ->
                            if (filtered.count { it == '.' } > 1) filtered.substring(0, filtered.lastIndexOf('.') + 1)
                            else filtered
                        }
                },
                label = { Text("Altura", color = textColor) },
                placeholder = { Text("Introduce tu altura (ej. 1.75)", color = textColor) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = sex,
                    onValueChange = { },
                    label = { Text("Sexo", color = textColor) },
                    placeholder = { Text("Selecciona tu sexo", color = textColor) },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedBorderColor = textColor,
                        unfocusedBorderColor = textColor,
                        focusedLabelColor = textColor,
                        unfocusedLabelColor = textColor
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sexOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, color = textColor) },
                            onClick = {
                                sex = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            Button(
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    navigateToPruebas(ageInt, sex)
                },
                enabled = isPruebasButtonEnabled
            ) {
                Text(text = "Ir a Pruebas")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val weightFloat = weight.toFloatOrNull() ?: 0f
                    val heightFloat = height.toFloatOrNull() ?: 0f
                    if (weightFloat > 0 && heightFloat > 0) {
                        val imc = weightFloat / (heightFloat * heightFloat)
                        navigateToIMC(imc)
                    }
                },
                enabled = isIMCButtonEnabled
            ) {
                Text(text = "Calcular IMC")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navigateToLogin() }
            ) {
                Text(text = "Volver al Login")
            }
        }
    }
}