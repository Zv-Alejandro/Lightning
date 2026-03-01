package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ies.wargame.domain.model.ActivityItem
import org.ies.wargame.domain.usecase.AddActivityUseCase
import org.ies.wargame.domain.usecase.DeleteActivityUseCase
import org.ies.wargame.domain.usecase.ListActivitiesUseCase
import org.ies.wargame.domain.usecase.UpdateActivityUseCase

class ActivitiesViewModel(
    private val listActivitiesUseCase: ListActivitiesUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase,
    private val addActivityUseCase: AddActivityUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase

) : ViewModel() {

    private val expandedIds = MutableStateFlow<Set<String>>(emptySet())
    val activities: StateFlow<List<ActivityItem>> =
        listActivitiesUseCase().combine(expandedIds) { firebaseList, expandedSet ->
            firebaseList.map {
                it.copy(expanded = expandedSet.contains(it.id))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleExpanded(id: String) {
        expandedIds.update { currentSet -> if (currentSet.contains(id)) currentSet - id else currentSet + id }
    }

    fun deleteActivity(id: String) {
        viewModelScope.launch {
            // Invoca el deleteUserUseCase
            deleteActivityUseCase(id)
        }
    }

    fun addActivity(activityItem: ActivityItem) {
        viewModelScope.launch {
            // Invoca el deleteUserUseCase
            addActivityUseCase(activityItem)
        }
    }

    fun updateActivity(activityItem: ActivityItem) {
        viewModelScope.launch {
            // Invoca el deleteUserUseCase
            updateActivityUseCase(activityItem)
        }
    }
}