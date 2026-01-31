package org.ies.wargame.presentation.ui.state

data class RegisterUiState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val emailError: String = "",
    val nameError: String = "",
    val passwordError: String = "",
    val registerSuccess: Boolean = false
)
