package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ies.wargame.domain.model.ActivityItem

class ActivitiesViewModel : ViewModel() {

    private val _activities = MutableStateFlow(
        listOf(
            ActivityItem(1, "Reunión padres", "Reunión informativa sobre el inicio de curso"),
            ActivityItem(
                2,
                "Entrega de notas",
                "Publicación de calificaciones del primer trimestre"
            ),
            ActivityItem(3, "Excursión", "Salida cultural al museo de ciencias")
        )
    )
    val activities: StateFlow<List<ActivityItem>> = _activities

    fun toggleExpanded(id: Int) {
        _activities.value = _activities.value.map {
            if (it.id == id) it.copy(expanded = !it.expanded) else it
        }
    }

    fun deleteActivity(id: Int) {
        _activities.value = _activities.value.filterNot { it.id == id }
    }

    fun addActivity(title: String, description: String) {
        val nextId = (_activities.value.maxOfOrNull { it.id } ?: 0) + 1
        _activities.value += ActivityItem(
                    id = nextId,
                    title = title,
                    description = description
                )
    }
    fun updateActivity(id: Int, title: String, description: String) {
        _activities.value = _activities.value.map {
            if (it.id == id) it.copy(title = title, description = description)
            else it
        }
    }
}