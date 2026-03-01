package org.ies.wargame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ies.wargame.presentation.ui.screen.*

@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Activities.route) {
            ActivitiesScreen(navController)
        }
        composable(Screen.AddActivity.route) {
            AddActivityScreen(navController)
        }
        composable(Screen.EditActivity.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            if (id.isNotEmpty()) {
            EditActivityScreen(navController, id)
        }
        }

    }
}


