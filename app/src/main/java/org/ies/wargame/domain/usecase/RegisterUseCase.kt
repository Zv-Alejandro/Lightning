package org.ies.wargame.domain.usecase

import org.ies.wargame.domain.model.User
import org.ies.wargame.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, name: String, password: String): User? {
        return repository.register(email, name, password)
    }
}