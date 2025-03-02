import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToPruebas: (Int, String) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToIMC: (Float) -> Unit
) {
    var age by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var sex by rememberSaveable { mutableStateOf("") }
    var isDarkMode by rememberSaveable { mutableStateOf(false) } // Estado para el modo oscuro

    var expanded by rememberSaveable { mutableStateOf(false) }
    val sexOptions = listOf("Hombre", "Mujer")

    // Validaciones para habilitar/deshabilitar botones
    val isIMCButtonEnabled = weight.isNotEmpty() && height.isNotEmpty() &&
            weight.toFloatOrNull() != null && height.toFloatOrNull() != null &&
            weight.toFloatOrNull()!! > 0 && height.toFloatOrNull()!! > 0

    val isPruebasButtonEnabled = age.isNotEmpty() && age.toIntOrNull() != null &&
            age.toIntOrNull()!! > 0 && sex.isNotEmpty()

    // Aplicar el tema basado en el modo oscuro
    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Switch para activar/desactivar el modo oscuro
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Modo Oscuro", modifier = Modifier.weight(1f))
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fila para el título y el ícono de impresión
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Espacio flexible para centrar el título
                Spacer(modifier = Modifier.weight(1f))

                Text(text = "HOME SCREEN", fontSize = 25.sp)

                Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para empujar el ícono a la derecha
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para la edad (solo números)
            OutlinedTextField(
                value = age,
                onValueChange = { newValue -> age = newValue.filter { it.isDigit() } },
                label = { Text("Edad") },
                placeholder = { Text("Introduce tu edad") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para el peso (números con decimales)
            OutlinedTextField(
                value = weight,
                onValueChange = { newValue ->
                    weight = newValue.filter { it.isDigit() || it == '.' || it == ',' }
                        .replace(',', '.')
                        .let { filtered -> if (filtered.count { it == '.' } > 1) filtered.substring(0, filtered.lastIndexOf('.') + 1) else filtered }
                },
                label = { Text("Peso") },
                placeholder = { Text("Introduce tu peso (ej. 70.5)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para la altura (números con decimales)
            OutlinedTextField(
                value = height,
                onValueChange = { newValue ->
                    height = newValue.filter { it.isDigit() || it == '.' || it == ',' }
                        .replace(',', '.')
                        .let { filtered -> if (filtered.count { it == '.' } > 1) filtered.substring(0, filtered.lastIndexOf('.') + 1) else filtered }
                },
                label = { Text("Altura") },
                placeholder = { Text("Introduce tu altura (ej. 1.75)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Menú desplegable para el sexo
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = sex,
                    onValueChange = { },
                    label = { Text("Sexo") },
                    placeholder = { Text("Selecciona tu sexo") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sexOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                sex = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            // Botón para navegar a la pantalla de pruebas
            Button(
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    navigateToPruebas(ageInt, sex)
                },
                enabled = isPruebasButtonEnabled // Habilitar solo si la edad y el sexo son válidos
            ) {
                Text(text = "Ir a Pruebas")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para calcular y navegar a la pantalla de IMC
            Button(
                onClick = {
                    val weightFloat = weight.toFloatOrNull() ?: 0f
                    val heightFloat = height.toFloatOrNull() ?: 0f
                    if (weightFloat > 0 && heightFloat > 0) {
                        val imc = weightFloat / (heightFloat * heightFloat)
                        navigateToIMC(imc) // Navegar a la pantalla de IMC
                    }
                },
                enabled = isIMCButtonEnabled // Habilitar solo si el peso y la altura son válidos
            ) {
                Text(text = "Calcular IMC")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para volver al login
            Button(
                onClick = { navigateToLogin() }
            ) {
                Text(text = "Volver al Login")
            }
        }
    }
}
