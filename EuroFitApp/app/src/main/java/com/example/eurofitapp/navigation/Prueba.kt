package com.example.eurofitapp.navigation

import androidx.annotation.DrawableRes

data class Prueba(
    var nombre: String,
    var categoria: String,
    @DrawableRes var Foto: Int,
    val descripcion: String
)
