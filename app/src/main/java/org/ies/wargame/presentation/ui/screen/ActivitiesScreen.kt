package org.ies.wargame.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ies.wargame.domain.model.ActivityItem
import org.ies.wargame.presentation.navigation.Screen
import org.ies.wargame.presentation.ui.components.MenuDeAcciones
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(
    navController: NavController,
    viewModel: ActivitiesViewModel
) {
    val activities = viewModel.activities.collectAsState().value
    var deleteActivity by remember { mutableStateOf<ActivityItem?>(null) }
    deleteActivity?.let { activityItem ->
        AlertDialog(
            onDismissRequest = { deleteActivity = null },
            title = { Text(text = "Eliminar actividad") },
            text = { Text("¿Estás seguro de que deseas eliminar la actividad ${activityItem.title}?") },
            confirmButton = {
                Button(onClick = { viewModel.deleteActivity(activityItem.id)
                    deleteActivity = null }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    deleteActivity = null
                }) { Text("Cancelar") }
            })
    }
    Scaffold(
        topBar = {
            MenuDeAcciones(navController,"Lista de actividades")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddActivity.route) }
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(activities) { activity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { viewModel.toggleExpanded(activity.id) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = activity.title, style = MaterialTheme.typography.titleMedium)

                        AnimatedVisibility(visible = activity.expanded) {
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                Text(text = activity.description)
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(onClick = {
                                        navController.navigate(Screen.EditActivity.routeWithId(activity.id))
                                    }) {
                                        Text("Modificar")
                                    }
                                    OutlinedButton(onClick = {deleteActivity = activity}) {
                                        Text("Eliminar")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun previewSt(){
    ActivitiesScreen(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}