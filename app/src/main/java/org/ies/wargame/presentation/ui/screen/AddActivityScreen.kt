package org.ies.wargame.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.ies.wargame.presentation.viewmodel.AddActivityViewModel
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityScreen(
    navController: NavController,
    activitiesViewModel: ActivitiesViewModel,
    viewModel: AddActivityViewModel = viewModel()

) {
    val title = viewModel.title.collectAsState().value
    val description = viewModel.description.collectAsState().value
    val titleError = viewModel.titleError.collectAsState().value
    val descriptionError = viewModel.descriptionError.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Añadir actividad") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = viewModel::setTitle,
                label = {
                    Text(
                        titleError.ifEmpty { "Título" },
                        color = if (titleError.isNotEmpty()) Red else Unspecified
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = viewModel::setDescription,
                label = {
                    Text(
                        descriptionError.ifEmpty { "Descripción" },
                        color = if (descriptionError.isNotEmpty()) Red else Unspecified
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (viewModel.validate()) {
                        activitiesViewModel.addActivity(title, description)
                        viewModel.clear()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}