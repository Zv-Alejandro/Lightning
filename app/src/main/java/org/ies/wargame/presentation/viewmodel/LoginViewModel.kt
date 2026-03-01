package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ies.wargame.domain.usecase.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible.asStateFlow()
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()
    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    fun setEmail(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun setPassword(value: String) {
        _password.value = value
        _passwordError.value = null
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun login(onSuccess: () -> Unit) {
        if (validate()) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                val result = loginUseCase(_email.value, _password.value)
                if (result) {
                    onSuccess()
                }
                else {
                    _emailError.value = "Credenciales inválidas"
                }
            } catch (e: Exception) {
                _emailError.value = "Error de conexión"
            } finally {
                _isLoading.value = false
            }
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (_email.value.isBlank()) {
            _emailError.value = "El email es obligatorio"
            isValid = false
        }
        if (_password.value.isBlank()) {
            _passwordError.value = "La contraseña es obligatoria"
            isValid = false
        }
        return isValid
    }
}