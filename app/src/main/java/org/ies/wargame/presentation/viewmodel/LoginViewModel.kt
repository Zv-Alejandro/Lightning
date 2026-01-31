package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.ies.wargame.data.repository.AuthRepositoryImpl
import org.ies.wargame.domain.usecase.LoginUseCase
import org.ies.wargame.presentation.ui.state.LoginUiState

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase(AuthRepositoryImpl())

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun setEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = "")
    }

    fun setPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, passwordError = "")
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            passwordVisible = !_uiState.value.passwordVisible
        )
    }

    fun login() {
        var valid = true

        if (_uiState.value.email.isBlank()) {
            _uiState.value = _uiState.value.copy(emailError = "Email is required")
            valid = false
        }

        if (_uiState.value.password.isBlank()) {
            _uiState.value = _uiState.value.copy(passwordError = "Password is required")
            valid = false
        }

        if (!valid) return

        val success = loginUseCase(
            _uiState.value.email,
            _uiState.value.password
        )

        _uiState.value = _uiState.value.copy(loginSuccess = success)
    }

    fun clear() {
        _uiState.value = LoginUiState()
    }
}
