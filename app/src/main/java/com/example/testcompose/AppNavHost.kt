package com.example.testcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(nav: NavHostController) {
    NavHost(nav, startDestination = "list") {
        composable("list") {
            ListScreen(nav)
        }
        composable("add") {
            AddScreen(nav)
        }
        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toInt() ?: 0
            DetailScreen(nav, id)
        }
    }
}