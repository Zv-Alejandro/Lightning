package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ies.wargame.data.repository.AuthRepositoryImpl
import org.ies.wargame.domain.usecase.RegisterUseCase
import org.ies.wargame.presentation.ui.state.RegisterUiState

class RegisterViewModel : ViewModel() {

    private val registerUseCase = RegisterUseCase(AuthRepositoryImpl())

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun setEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = "")
    }

    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(name = name, nameError = "")
    }

    fun setPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, passwordError = "")
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            passwordVisible = !_uiState.value.passwordVisible
        )
    }

    fun register() {
        var valid = true

        if (_uiState.value.email.isBlank()) {
            _uiState.value = _uiState.value.copy(emailError = "Email is required")
            valid = false
        }

        if (_uiState.value.name.isBlank()) {
            _uiState.value = _uiState.value.copy(nameError = "Name is required")
            valid = false
        }

        if (_uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(passwordError = "Password is required")
            valid = false
        }

        if (!valid) return

        val user = registerUseCase(
            _uiState.value.email,
            _uiState.value.name,
            _uiState.value.password
        )

        _uiState.value = _uiState.value.copy(registerSuccess = user != null)
    }

    fun clear() {
        _uiState.value = RegisterUiState()
    }
}
