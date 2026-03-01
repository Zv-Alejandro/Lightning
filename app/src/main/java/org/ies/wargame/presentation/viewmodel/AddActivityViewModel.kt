package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ies.wargame.domain.model.ActivityItem
import org.ies.wargame.domain.usecase.AddActivityUseCase

class AddActivityViewModel(private val addActivityUseCase: AddActivityUseCase) : ViewModel() {
    private val _activity = MutableStateFlow(ActivityItem(id = "", title = "", description = ""))
    val activity: StateFlow<ActivityItem> = _activity.asStateFlow()
    private val _titleError = MutableStateFlow<String?>(null)
    val titleError: StateFlow<String?> = _titleError.asStateFlow()
    private val _descriptionError = MutableStateFlow<String?>(null)
    val descriptionError: StateFlow<String?> = _descriptionError.asStateFlow()
    fun setTitle(title: String) {
        _activity.value = _activity.value.copy(title = title)
        _titleError.value = null
    }

    fun setDescription(description: String) {
        _activity.value = _activity.value.copy(description = description)
        _descriptionError.value = null
    }

    fun clear() {
        _activity.value =
            ActivityItem("", "", "")
        _titleError.value = null
        _descriptionError.value = null
    }

    fun saveActivity(onSuccess: () -> Unit) {
        if (validate()) {
            viewModelScope.launch {
                addActivityUseCase(_activity.value.copy(id = ""))
                clear()
                onSuccess()
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (_activity.value.title.isBlank()) {
            _titleError.value = "Título requerido"
            isValid = false
        }
        if (_activity.value.description.isBlank()) {
            _descriptionError.value = "Descripción requerida"
            isValid = false
        }
        return isValid
    }
}
