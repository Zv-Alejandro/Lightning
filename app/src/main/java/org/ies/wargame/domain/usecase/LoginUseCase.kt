package org.ies.wargame.domain.usecase

import org.ies.wargame.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String): Boolean {
        return repository.login(email, password)
    }
}