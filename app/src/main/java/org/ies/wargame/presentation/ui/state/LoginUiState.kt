package org.ies.wargame.presentation.ui.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val loginSuccess: Boolean = false
)
