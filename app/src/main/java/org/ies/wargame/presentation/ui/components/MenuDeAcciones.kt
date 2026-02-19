package org.ies.wargame.presentation.ui.components
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import org.ies.wargame.presentation.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MenuDeAcciones(navController: NavController, title: String) {
    // Estado para controlar la visibilidad del menú
    var expanded by remember { mutableStateOf(false) }

    // Barra de herramientas (TopAppBar)
    TopAppBar(
        title = { Text(title) },
        actions = {
            // Botón de menú
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú"
                )
            }

            // Menú desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Mostrar las actividades") },
                    onClick = {
                        // Acción 1
                        expanded = false
                        navController.navigate(Screen.Activities.route)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Añadir actividad") },
                    onClick = {
                        // Acción 1
                        expanded = false
                        navController.navigate(Screen.AddActivity.route)
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Back")
                    },
                    onClick = {
                        // Acción 2
                        expanded = false
                        navController.popBackStack()
                    }
                )
                // Línea divisoria entre elementos
                HorizontalDivider()
                DropdownMenuItem(
                    text = {
                        Text("Cerrar menú desplegable")
                    },
                    onClick = {
                        // Simplemente cierra el menú desplegable
                        expanded = false
                    }
                )
            }
        }
    )
}