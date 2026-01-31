package org.ies.wargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _emailError = MutableStateFlow("")
    val emailError: StateFlow<String> = _emailError

    private val _passwordError = MutableStateFlow("")
    val passwordError: StateFlow<String> = _passwordError

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun setEmail(value: String) {
        _email.value = value
        _emailError.value = ""
    }

    fun setPassword(value: String) {
        _password.value = value
        _passwordError.value = ""
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun login() {
        var valid = true

        if (_email.value.isBlank()) {
            _emailError.value = "Email is required"
            valid = false
        }

        if (_password.value.isBlank()) {
            _passwordError.value = "Password is required"
            valid = false
        }

        if (!valid) return

        _loginSuccess.value = true
    }
}