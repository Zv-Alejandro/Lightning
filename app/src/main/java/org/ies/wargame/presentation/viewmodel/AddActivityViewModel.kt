package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddActivityViewModel : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _titleError = MutableStateFlow("")
    val titleError: StateFlow<String> = _titleError

    private val _descriptionError = MutableStateFlow("")
    val descriptionError: StateFlow<String> = _descriptionError

    fun setTitle(value: String) {
        _title.value = value
        _titleError.value = ""
    }

    fun setDescription(value: String) {
        _description.value = value
        _descriptionError.value = ""
    }

    fun validate(): Boolean {
        var valid = true

        if (_title.value.isBlank()) {
            _titleError.value = "Título requerido"
            valid = false
        }

        if (_description.value.isBlank()) {
            _descriptionError.value = "Descripción requerida"
            valid = false
        }

        return valid
    }

    fun clear() {
        _title.value = ""
        _description.value = ""
        _titleError.value = ""
        _descriptionError.value = ""
    }
}