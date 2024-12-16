package com.example.stock_it

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stock_it.ui.theme.Stock_itTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Stock_itTheme {
                // Pasar un valor predeterminado para la vista previa en lugar de la base de datos
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController() // Controlador para gestionar la navegación
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // Barra inferior personalizada
    ) { innerPadding ->
        // No pasamos la base de datos
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "inventory", // Pantalla inicial
        modifier = modifier
    ) {
        composable("inventory") {
            InventoryScreen() // Solo pasamos el navController
        }
        composable("shopping_list") {
            ShoppingListScreen() // No necesitamos la base de datos aquí
        }
        composable("suppliers") {
            SuppliersScreen() // No necesitamos la base de datos aquí
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Stock_itTheme {
        // Vista previa sin la base de datos
        MainScreen()
    }
}
