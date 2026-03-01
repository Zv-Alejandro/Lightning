package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ies.wargame.domain.model.ActivityItem
import org.ies.wargame.domain.usecase.AddActivityUseCase
import org.ies.wargame.domain.usecase.UpdateActivityUseCase

class EditActivityViewModel(
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val addActivityUseCase: AddActivityUseCase
) : ViewModel() {
    // Estado del formulario
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()
    private val _description = MutableStateFlow("")
    val description: StateFlow<String> =
        _description.asStateFlow()

    // Estado de errores
    private val _titleError = MutableStateFlow<String?>(null)
    val titleError: StateFlow<String?> = _titleError.asStateFlow()
    private val _descriptionError = MutableStateFlow<String?>(null)
    val descriptionError: StateFlow<String?> =
        _descriptionError.asStateFlow()

    // Para saber si estamos editando o creando uno nuevo
    private var currentActivityId: String? = null
    fun load(activity: ActivityItem) {
        currentActivityId = activity.id
        _title.value = activity.title
        _description.value = activity.description
    }

    fun setTitle(value: String) {
        _title.value = value
        _titleError.value = null
    }

    fun setDescription(value: String) {
        _description.value = value
        _descriptionError.value = null
    }

    fun saveActivity(onSuccess: () -> Unit) {
        if (validate()) {
            viewModelScope.launch {
                val item = ActivityItem(
                    id = currentActivityId
                        ?: "",
                    // Si es nulo, Firebase generará uno en .add()
                    title = _title . value, description = _description.value)
                if (currentActivityId == null) {
                addActivityUseCase(
                    item
                )
            } else { updateActivityUseCase(item) }
                onSuccess()
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (_title.value.isBlank()) {
            _titleError.value = "Título requerido"
            isValid = false
        }
        if (_description.value.isBlank()) {
            _descriptionError.value = "Descripción requerida"
            isValid = false
        }
        return isValid
    }
}
