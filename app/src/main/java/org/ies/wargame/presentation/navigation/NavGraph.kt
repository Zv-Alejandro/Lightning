package org.ies.wargame.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ies.wargame.presentation.ui.screen.LoginScreen
import org.ies.wargame.presentation.ui.screen.RegisterScreen
import org.ies.wargame.presentation.ui.screen.ActivitiesScreen
import org.ies.wargame.presentation.ui.screen.AddActivityScreen

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
    }
}
