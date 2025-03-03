package com.example.eurofitapp.navigation

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
fun LoginScreen(navigateToHome: () -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) } // Estado para ver/ocultar contraseña
    var errorMessage by rememberSaveable { mutableStateOf("") }

    // Estado mutable para el usuario y contraseña
    var storedUsername by rememberSaveable { mutableStateOf("root") }
    var storedPassword by rememberSaveable { mutableStateOf("root") }

    // Controla si se muestra el diálogo de cambio de contraseña
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) } // Estado para ver/ocultar nueva contraseña

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "INICIAR SESIÓN", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña con opción de ver/ocultar
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de error si las credenciales son incorrectas
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 🔹 Botones en la misma fila con espacio entre ellos
        Row {
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

        Spacer(modifier = Modifier.weight(1f))
    }

    // 🔹 Diálogo para cambiar contraseña
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Cambiar contraseña") },
            text = {
                Column {
                    Text(text = "Ingrese su nueva contraseña:")
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nueva Contraseña") },
                        visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                                Icon(
                                    imageVector = if (newPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (newPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                )
                            }
                        }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (newPassword.isNotBlank()) {
                        storedPassword = newPassword
                        errorMessage = "Contraseña actualizada con éxito."
                        showDialog = false
                    } else {
                        errorMessage = "La nueva contraseña no puede estar vacía."
                    }
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
