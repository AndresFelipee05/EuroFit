package com.example.eurofitapp.navigation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eurofitapp.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PruebaStickyView(
    searchText: String,
    selectedCategory: String,
    userAge: Int,
    userSex: String,
    navController: NavController
) {
    val context = LocalContext.current

    // Filtrar las pruebas según la edad y el sexo
    val pruebasFiltradas = getPruebas().map {
        if (it.nombre == "Lanzar Balon 3kg" && userSex == "Mujer") {
            it.copy(nombre = "Lanzar Balon 2kg")
        } else {
            it
        }
    }.filter {
        (selectedCategory == "Mostrar todas" || it.categoria == selectedCategory) &&
                (it.nombre.contains(searchText, ignoreCase = true) ||
                        it.categoria.contains(searchText, ignoreCase = true)) &&
                // Filtrar por edad para las pruebas restringidas
                (it.nombre != "Velocidad 5x10" &&
                        it.nombre != "Lanzar Balon 3kg" &&
                        it.nombre != "Lanzar Balon 2kg" ||
                        (it.nombre == "Velocidad 5x10" && userAge >= 14) ||
                        ((it.nombre == "Lanzar Balon 3kg" || it.nombre == "Lanzar Balon 2kg") && userAge >= 15))
    }

    val pruebasAgrupadas = pruebasFiltradas.groupBy { it.categoria }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        pruebasAgrupadas.forEach { (categoria, pruebas) ->
            stickyHeader {
                Text(
                    text = categoria,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .padding(8.dp),
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
            items(pruebas) { prueba ->
                ItemPrueba(
                    prueba = prueba,
                    onItemSelected = {
                        Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show()
                    },
                    userAge = userAge,
                    userSex = userSex,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun SearchView(searchText: String, onSearchTextChanged: (String) -> Unit) {
    androidx.compose.material3.TextField(
        value = searchText,
        onValueChange = { onSearchTextChanged(it) },
        placeholder = { Text("Buscar prueba...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}

@Composable
fun CategoryDropdown(
    categorias: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Categoría: $selectedCategory",
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
                .background(Color.LightGray)
                .padding(8.dp)
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(text = categoria) },
                    onClick = {
                        onCategorySelected(categoria)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun PruebasScreen(
    navigateToHome: () -> Unit,
    userAge: Int,
    userSex: String,
    navController: NavController
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("Mostrar todas") }

    val categorias = listOf("Mostrar todas") + getPruebas().map { it.categoria }.distinct()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "PRUEBAS", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar un mensaje si el usuario es menor de 14 años
        if (userAge < 14) {
            Text(
                text = "La prueba de velocidad 5x10 está disponible solo para mayores de 14 años.",
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Mostrar un mensaje si el usuario es menor de 15 años
        if (userAge < 15) {
            Text(
                text = "La prueba de lanzamiento de balón está disponible solo para mayores de 15 años.",
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Barra de búsqueda
        SearchView(searchText) { newText -> searchText = newText }

        // Menú desplegable para seleccionar categoría
        CategoryDropdown(categorias, selectedCategory) { newCategory ->
            selectedCategory = newCategory
        }

        // Lista filtrada de pruebas
        PruebaStickyView(
            searchText,
            selectedCategory,
            userAge,
            userSex,
            navController
        ) // Pasar el navController

        Button(onClick = { navigateToHome() }) {
            Text(text = "Volver a Home")
        }
    }
}

@Composable
fun ItemPrueba(
    prueba: Prueba,
    onItemSelected: (Prueba) -> Unit,
    userAge: Int,
    userSex: String,
    navController: NavController
) {
    val context = LocalContext.current
    var showDialog by rememberSaveable { mutableStateOf(false) }

    // Determinar si la prueba está deshabilitada
    val isDisabled =
        (prueba.nombre == "Velocidad 5x10" && userAge < 14) ||
                (prueba.nombre == "Lanzar Balon 3kg" && userAge < 15)

    Card(
        border = BorderStroke(2.dp, if (isDisabled) Color.Gray else Color.Blue),
        modifier = Modifier
            .width(200.dp)
            .clickable(enabled = !isDisabled) {
                // Verificar si la prueba seleccionada es "Abdominales 30º" y el género es mujer
                if (prueba.nombre == "Abdominales 30º" && userSex == "Mujer") {
                    // Navegar a la pantalla de abdominales para mujeres
                    navController.navigate("abdominales_mujer_screen/${userAge}/${userSex}")
                } else if (prueba.nombre == "Flexibilidad" && userSex == "Hombre") {
                    // Navegar a la pantalla de flexibilidad para hombres
                    navController.navigate("flexibilidad_hombre_screen/${userAge}")
                } else if (prueba.nombre == "Flexibilidad" && userSex == "Mujer") {
                    // Navegar a la pantalla de flexibilidad para mujeres
                    navController.navigate("flexibilidad_mujer_screen/${userAge}")
                } else if (prueba.nombre == "Test Cooper" && userSex == "Hombre") {
                    // Navegar a la pantalla de Cooper para hombres
                    navController.navigate("cooper_hombre_screen/${userAge}")
                } else if (prueba.nombre == "Test Cooper" && userSex == "Mujer") {
                    // Navegar a la pantalla de Cooper para mujeres
                    navController.navigate("cooper_mujer_screen/${userAge}")
                } else if (prueba.nombre == "Velocidad 5x10" && userSex == "Hombre") {
                    // Navegar a la pantalla de velocidad para hombres
                    navController.navigate("velocidad_hombre_screen/${userAge}")
                } else if (prueba.nombre == "Velocidad 5x10" && userSex == "Mujer") {
                    // Navegar a la pantalla de velocidad para mujeres
                    navController.navigate("velocidad_mujer_screen/${userAge}")
                } else if (prueba.nombre == "Lanzar Balon 3kg" && userSex == "Hombre") {
                    // Navegar a la pantalla de lanzamiento para hombres
                    navController.navigate("lanzar_hombre_screen/${userAge}")
                } else if (prueba.nombre == "Lanzar Balon 2kg" && userSex == "Mujer") {
                    // Navegar a la pantalla de lanzamiento para mujeres
                    navController.navigate("lanzar_mujer_screen/${userAge}")
                } else if (prueba.nombre == "Abdominales 30º" && userSex == "Hombre") {
                    // Navegar a la pantalla de prueba de la manera habitual
                    navController.navigate("nota/${prueba.nombre}/${userAge}/${userSex}")
                } else {
                    println("error")
                }
            }
    ) {
        Column {
            Image(
                painter = painterResource(id = prueba.Foto),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = prueba.nombre,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = if (isDisabled) Color.Gray else Color.Unspecified
            )
            Text(
                text = prueba.categoria,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp,
                color = if (isDisabled) Color.Gray else Color.Unspecified
            )
            // Enlace para mostrar información
            Text(
                text = "¿Qué es esta prueba?",
                color = if (isDisabled) Color.Gray else Color.Blue,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .clickable(enabled = !isDisabled) {
                        showDialog = true
                    }
            )
        }
    }

    // Muestra un diálogo con la descripción
    if (showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = prueba.nombre) },
            text = { Text(text = prueba.descripcion) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cerrar")
                }
            }
        )
    }
}

fun getPruebas(): List<Prueba> {

    return listOf(
        Prueba(
            "Abdominales 30º",
            "Coordinacion",
            R.drawable.abdominales,
            "Evalúa la resistencia muscular de los abdominales mediante la ejecución de una serie de repeticiones en un tiempo determinado. " +
                    "El participante se acuesta boca arriba con las rodillas flexionadas y los pies apoyados en el suelo." +
                    " Con las manos en la nuca, debe realizar la mayor cantidad de abdominales en 30 segundos sin perder la técnica adecuada."
        ),
        Prueba(
            "Flexibilidad",
            "Flexibilidada",
            R.drawable.flexibilidad,
            "Mide la capacidad de elongación de los músculos, en especial los isquiotibiales y la zona lumbar." +
                    " Se usa la prueba de flexión profunda (test de Sit and Reach)." +
                    " El participante se sienta con las piernas extendidas y juntas," +
                    " intentando alcanzar con las manos la mayor distancia posible sobre una caja o una regla horizontal."
        ),
        Prueba(
            "Test Cooper",
            "Resistencia",
            R.drawable.cooper,
            "Evalúa la resistencia aeróbica midiendo la distancia recorrida en un tiempo determinado." +
                    "El participante debe correr durante 12 minutos a la máxima intensidad posible en una pista o campo." +
                    " La distancia total recorrida se usa para estimar el consumo máximo de oxígeno (VO₂ máx)," +
                    " un indicador clave de la resistencia cardiovascular."
        ),
        Prueba(
            "Velocidad 5x10",
            "Velocidad",
            R.drawable.velocidad,
            " Mide la agilidad y velocidad en desplazamientos cortos con cambios de dirección." +
                    "Se colocan dos líneas separadas por 5 metros." +
                    " El participante debe recorrer la distancia de ida y vuelta 5 veces lo más rápido posible (total de 50 metros)." +
                    " Se mide el tiempo total desde la salida hasta la última llegada."
        ),
        Prueba(
            "Lanzar Balon 3kg",
            "Fuerza nuscular",
            R.drawable.balon,
            "Evalúa la potencia explosiva del tren superior." +
                    "El participante se coloca de pie con los pies separados al ancho de los hombros y lanza un balón medicinal de 3 kg con ambas manos desde el pecho hacia adelante." +
                    " La distancia alcanzada se mide desde la línea de lanzamiento hasta donde cae el balón."
        )
    )
}