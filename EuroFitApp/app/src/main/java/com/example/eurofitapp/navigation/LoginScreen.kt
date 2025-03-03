package com.example.eurofitapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    themeViewModel: ThemeViewModel,  // Pasamos el ViewModel aquí
    isDarkMode: Boolean  // Usamos el valor de isDarkMode aquí
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    var storedUsername by rememberSaveable { mutableStateOf("root") }
    var storedPassword by rememberSaveable { mutableStateOf("root") }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }

    // El valor de isDarkMode ya está pasado desde NavigationWrapper
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp), // Agregamos padding general
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título y Switch en la misma fila bien alineados
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Espaciado
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "INICIAR SESIÓN", fontSize = 25.sp, color = textColor)
            Switch(
                checked = isDarkMode,
                onCheckedChange = { themeViewModel.toggleTheme() } // Cambiamos el tema desde aquí
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario", color = textColor) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = textColor) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = textColor
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Centrar los botones correctamente
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Button(onClick = {
                    if (username == storedUsername && password == storedPassword) {
                        errorMessage = ""
                        navigateToHome() // Navega a la pantalla de inicio
                    } else {
                        errorMessage = "Usuario o contraseña incorrectos."
                    }
                }) {
                    Text(text = "Iniciar sesión")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    if (username == storedUsername) {
                        showDialog = true
                        errorMessage = ""
                    } else {
                        errorMessage = "Usuario no válido para cambiar la contraseña."
                    }
                }) {
                    Text(text = "Cambiar contraseña")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
