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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.ies.wargame.presentation.navigation.Screen
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(
    navController: NavController,
    viewModel: ActivitiesViewModel
) {
    val activities = viewModel.activities.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Actividades escolares") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                    }
                }
            )
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
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.toggleExpanded(activity.id) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = activity.title, style = MaterialTheme.typography.titleMedium)

                        AnimatedVisibility(visible = activity.expanded) {
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                Text(text = activity.description)
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(onClick = { /* modificar */ }) {
                                        Text("Modificar")
                                    }
                                    OutlinedButton(onClick = { viewModel.deleteActivity(activity.id) }) {
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