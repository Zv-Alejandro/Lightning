package org.ies.wargame.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ies.wargame.presentation.ui.state.AddActivityUiState

class AddActivityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddActivityUiState())
    val uiState: StateFlow<AddActivityUiState> = _uiState

    fun setTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title, titleError = "")
    }

    fun setDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description, descriptionError = "")
    }

    fun validate(): Boolean {
        var valid = true
        var state = _uiState.value

        if (state.title.isBlank()) {
            state = state.copy(titleError = "Título requerido")
            valid = false
        }
        if (state.description.isBlank()) {
            state = state.copy(descriptionError = "Descripción requerida")
            valid = false
        }

        _uiState.value = state
        return valid
    }

    fun clear() {
        _uiState.value = AddActivityUiState()
    }
}
