package com.example.eurofitapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(navigateToHome: () -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Credenciales almacenadas (puedes cambiarlas o hacer que se guarden en una base de datos)
    val storedUsername = "root"
    val storedPassword = "root"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "LOGIN SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(0.5f))

        // Campo de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true
        )
        Spacer(modifier = Modifier.weight(0.2f))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Spacer(modifier = Modifier.weight(0.2f))

        // Mostrar mensaje de error si las credenciales son incorrectas
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.weight(0.5f))

        // Botón para iniciar sesión
        Button(onClick = {
            if (username == storedUsername && password == storedPassword) {
                errorMessage = ""
                navigateToHome()
            } else {
                errorMessage = "Usuario o contraseña incorrectos."
            }
        }) {
            Text(text = "Iniciar sesión")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
