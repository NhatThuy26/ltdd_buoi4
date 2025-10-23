package com.example.btapb4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btapb4.screen.Screen1

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "screen1") {

        composable("screen1") {
            Screen1(navController = navController)
        }

        composable("screen2") {
            Screen2(navController = navController)
        }

        composable("screen3") {
            Screen3(navController = navController)
        }

        composable("screen4") {
            Screen4(navController = navController)
        }
    }
}
