package org.ies.wargame.domain.usecase

import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.data.repositories.AuthFirestoreRepository

class LoginUseCase(private val repository: AuthFirestoreRepository) {
    suspend operator fun invoke(email: String, pass: String): Boolean {
        return repository.login(email, pass)
    }
}