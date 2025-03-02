import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eurofitapp.navigation.AbdominalesMujerScreen
import com.example.eurofitapp.navigation.CooperMujerScreen
import com.example.eurofitapp.navigation.IMCScreen
import com.example.eurofitapp.navigation.LanzarHombreScreen
import com.example.eurofitapp.navigation.LanzarMujerScreen
import com.example.eurofitapp.navigation.LoginScreen
import com.example.eurofitapp.navigation.NotaScreen
import com.example.eurofitapp.navigation.PruebasScreen
import com.example.eurofitapp.navigation.VelocidadHombreScreen
import com.example.eurofitapp.navigation.VelocidadMujerScreen
import com.example.eurofitapp.screens.*

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Pantalla Login
        composable("login") {
            LoginScreen { navController.navigate("home") }
        }

        // Pantalla Home
        composable("home") {
            HomeScreen(
                navigateToPruebas = { age, sex ->
                    navController.navigate("pruebas/$age/$sex")
                },
                navigateToLogin = { navController.navigate("login") },
                navigateToIMC = { imc ->
                    navController.navigate("imc_screen/$imc")
                }
            )
        }

        // Pantalla Pruebas con parámetros de edad y sexo
        composable("pruebas/{age}/{sex}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0
            val sex = backStackEntry.arguments?.getString("sex") ?: "Desconocido"

            PruebasScreen(
                navigateToHome = { navController.navigate("home") },
                userAge = age,
                userSex = sex,
                navController = navController // Pasamos navController también
            )
        }

        // Pantalla NotaScreen para introducir los datos necesarios para calcular la nota
        composable("nota/{pruebaNombre}/{age}/{sex}") { backStackEntry ->
            val pruebaNombre = backStackEntry.arguments?.getString("pruebaNombre") ?: ""
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0
            val sex = backStackEntry.arguments?.getString("sex") ?: "Desconocido"

            NotaScreen(
                pruebaNombre = pruebaNombre,
                userAge = age,
                userSex = sex
            )
        }

        // ruta para AbdominalesMujerScreen
        composable("abdominales_mujer_screen/{age}/{sex}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0
            val sex = backStackEntry.arguments?.getString("sex") ?: "Desconocido"

            AbdominalesMujerScreen(userAge = age, userSex = sex)
        }

        // ruta para FlexibilidadHombreScreen
        composable("flexibilidad_hombre_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            FlexibilidadHombreScreen(userAge = age)
        }

        // ruta para FlexibilidadMujerScreen
        composable("flexibilidad_mujer_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            FlexibilidadMujerScreen(userAge = age)
        }

        // ruta para CooperHombreScreen
        composable("cooper_hombre_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            CooperHombreScreen(userAge = age)
        }

        // ruta para CooperMujerScreen
        composable("cooper_mujer_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            CooperMujerScreen(userAge = age)
        }

        // ruta para VelocidadHombreScreen
        composable("velocidad_hombre_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            VelocidadHombreScreen(userAge = age)
        }

        // ruta para VelocidadMujerScreen
        composable("velocidad_mujer_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            VelocidadMujerScreen(userAge = age)
        }

        // ruta para LanzarHombreScreen
        composable("lanzar_hombre_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            LanzarHombreScreen(userAge = age)
        }

        // ruta para LanzarMujerScreen
        composable("lanzar_mujer_screen/{age}") { backStackEntry ->
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0

            LanzarMujerScreen(userAge = age)
        }

        // ruta para IMCScreen
        composable("imc_screen/{imc}") { backStackEntry ->
            val imc = backStackEntry.arguments?.getString("imc")?.toFloatOrNull() ?: 0f
            IMCScreen(
                imc = imc,
                navigateToHome = { navController.navigate("home") } // Navegar a HomeScreen
            )
        }
    }
}