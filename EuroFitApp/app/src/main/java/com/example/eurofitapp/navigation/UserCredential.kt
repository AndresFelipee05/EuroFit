package com.example.eurofitapp.navigation

data class UserCredential(val username: String, val password: String)

// Objeto singleton para gestionar las credenciales (persiste mientras la app está en memoria)
object CredentialsManager {
    // Lista mutable para almacenar las credenciales
    private val credentials = mutableListOf(UserCredential("root", "root"))

    // Función para validar credenciales
    fun validateCredentials(username: String, password: String): Boolean {
        return credentials.any { it.username == username && it.password == password }
    }

    // Función para agregar nuevas credenciales
    fun addCredentials(username: String, password: String): Boolean {
        // Verificar si el usuario ya existe
        if (credentials.any { it.username == username }) {
            return false // No se puede agregar, ya existe
        }
        credentials.add(UserCredential(username, password))
        return true
    }

    // Función para obtener todas las credenciales (para depuración)
    fun getAllCredentials(): List<UserCredential> {
        return credentials.toList()
    }
}