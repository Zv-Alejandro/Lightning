package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ies.wargame.presentation.ui.state.ActivitiesUiState
import org.ies.wargame.presentation.ui.state.ActivityItemUi

class ActivitiesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ActivitiesUiState(
            activities = listOf(
                ActivityItemUi(1, "Reunión padres", "Reunión informativa sobre el inicio de curso"),
                ActivityItemUi(2, "Entrega de notas", "Publicación de calificaciones del primer trimestre"),
                ActivityItemUi(3, "Excursión", "Salida cultural al museo de ciencias")
            )
        )
    )
    val uiState: StateFlow<ActivitiesUiState> = _uiState

    fun toggleExpanded(id: Int) {
        _uiState.value = _uiState.value.copy(
            activities = _uiState.value.activities.map {
                if (it.id == id) it.copy(expanded = !it.expanded) else it
            }
        )
    }

    fun deleteActivity(id: Int) {
        _uiState.value = _uiState.value.copy(
            activities = _uiState.value.activities.filterNot { it.id == id }
        )
    }

    fun addActivity(title: String, description: String) {
        val nextId = (_uiState.value.activities.maxOfOrNull { it.id } ?: 0) + 1
        _uiState.value = _uiState.value.copy(
            activities = _uiState.value.activities + ActivityItemUi(
                id = nextId,
                title = title,
                description = description
            )
        )
    }
}
