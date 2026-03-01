package org.ies.wargame.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ies.wargame.presentation.ui.components.MenuDeAcciones
import org.ies.wargame.presentation.viewmodel.EditActivityViewModel
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditActivityScreen(
    navController: NavController,
    id: String,
    activitiesViewModel: ActivitiesViewModel = koinViewModel(),
    viewModel: EditActivityViewModel = koinViewModel()
) {
    val activities by activitiesViewModel.activities.collectAsState()
    val activity = remember(activities) {
        activities.find { it.id == id }
    }
// 3. Cargamos los datos solo cuando la actividad esté lista
    LaunchedEffect(activity) {
        activity?.let { viewModel.load(it) }
    }

// Observamos los estados (usando delegados 'by' para limpiar el código)
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val titleError by viewModel.titleError.collectAsState()
    val descriptionError by viewModel.descriptionError.collectAsState()
    Scaffold(topBar = { MenuDeAcciones(navController, "Editar actividad") }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = viewModel::setTitle,
                isError = !titleError.isNullOrEmpty(),
                // Indica visualmente el error
                label = { Text(titleError ?: "Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value =
                    description,
                onValueChange = viewModel::setDescription,
                isError = !descriptionError.isNullOrEmpty(),
                label = {
                    Text(
                        descriptionError ?: "Descripción"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                // 4. Usamos la función de guardado del ViewModel de edición
                viewModel.saveActivity { navController.popBackStack() }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Guardar cambios")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EditionPreview() {
    EditActivityScreen(
        navController = rememberNavController(),
        "actividad_1"
    )
}