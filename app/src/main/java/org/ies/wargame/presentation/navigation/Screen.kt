package org.ies.wargame.presentation.navigation


sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Activities : Screen("activities")
    data object AddActivity : Screen("add_activity")
    data object EditActivity : Screen("edit_activity/{id}") {
        fun routeWithId(id: String) = "edit_activity/$id"
    }

}
