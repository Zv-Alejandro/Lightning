package org.ies.wargame.domain.usecase

import org.ies.wargame.data.repositories.AuthFirestoreRepository

class RegisterUseCase(private val repository: AuthFirestoreRepository) {
    suspend operator fun invoke(email: String, pass: String, name: String): Boolean {
        return repository.register(email, pass)
    }
}