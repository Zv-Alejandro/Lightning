package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _emailError = MutableStateFlow("")
    val emailError: StateFlow<String> = _emailError

    private val _nameError = MutableStateFlow("")
    val nameError: StateFlow<String> = _nameError

    private val _passwordError = MutableStateFlow("")
    val passwordError: StateFlow<String> = _passwordError

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess: StateFlow<Boolean> = _registerSuccess

    fun setEmail(value: String) {
        _email.value = value
        _emailError.value = ""
    }

    fun setName(value: String) {
        _name.value = value
        _nameError.value = ""
    }

    fun setPassword(value: String) {
        _password.value = value
        _passwordError.value = ""
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun register() {
        var valid = true

        if (_email.value.isBlank()) {
            _emailError.value = "Email requerido"
            valid = false
        }

        if (_name.value.isBlank()) {
            _nameError.value = "Nombre requerido"
            valid = false
        }

        if (_password.value.isBlank()) {
            _passwordError.value = "Contraseña requerida"
            valid = false
        }

        if (!valid) return

        _registerSuccess.value = true
    }
}