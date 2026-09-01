package com.example.androiddbconnectivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddbconnectivity.ui.theme.AndroidDBConnectivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHelper = DBHelper(this)
        enableEdgeToEdge()
        setContent {
            AndroidDBConnectivityTheme {
                AppNavigation(dbHelper)
            }
        }
    }
}

@Composable
fun AppNavigation(dbHelper: DBHelper) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                dbHelper = dbHelper,
                onLoginSuccess = { email ->
                    navController.navigate("dashboard/$email") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                dbHelper = dbHelper,
                onRegisterSuccess = {
                    navController.navigate("login")
                }
            )
        }
        composable("dashboard/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            DashboardScreen(
                dbHelper = dbHelper,
                email = email,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard/{email}") { inclusive = true }
                    }
                }
            )
        }
    }
}