package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ies.wargame.domain.usecase.RegisterUseCase

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError = _emailError.asStateFlow()
    private val _nameError = MutableStateFlow<String?>(null)
    val nameError = _nameError.asStateFlow()
    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    fun setEmail(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun setName(value: String) {
        _name.value = value
        _nameError.value = null
    }

    fun setPassword(value: String) {
        _password.value = value
        _passwordError.value = null
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun register(onSuccess: () -> Unit) {
        if (validate()) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                val success = registerUseCase(_email.value, _password.value, _name.value)
                    if (success) {
                    onSuccess()
                } else {
                    _emailError.value = "Error al crear la cuenta"
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
            _emailError.value = "Email requerido"
            isValid = false
        }
        if (_name.value.isBlank()) {
            _nameError.value = "Nombre requerido"
            isValid = false
        }
        if (_password.value.isBlank() || _password.value.length < 6) {
            _passwordError.value = "Contraseña requerida (mín. 6 caracteres)"
            isValid = false
        }
        return isValid
    }
}