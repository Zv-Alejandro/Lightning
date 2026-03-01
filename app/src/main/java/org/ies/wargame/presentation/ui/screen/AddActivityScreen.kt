package org.ies.wargame.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.ies.wargame.presentation.ui.components.MenuDeAcciones
import org.ies.wargame.presentation.viewmodel.AddActivityViewModel
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityScreen(navController: NavController, viewModel: AddActivityViewModel = koinViewModel()) {
    val currentActivity by viewModel.activity.collectAsState()
    val titleError by viewModel.titleError.collectAsState()
    val descriptionError by viewModel.descriptionError.collectAsState()
    Scaffold(topBar = {
        MenuDeAcciones(
            navController,
            "Añadir actividad"
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = currentActivity.title,
                onValueChange = viewModel::setTitle,
                isError = titleError != null,
                label = { Text(titleError ?: "Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value =
                    currentActivity.description,
                onValueChange = viewModel::setDescription,
                isError = descriptionError != null,
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
            Button(
                onClick =
                { viewModel.saveActivity { navController.popBackStack() } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Guardar"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AdditionalPreview() {
    AddActivityScreen(
        navController = rememberNavController()
    )
}