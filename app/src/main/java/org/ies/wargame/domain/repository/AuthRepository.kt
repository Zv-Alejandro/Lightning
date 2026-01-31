package org.ies.wargame.domain.repository

import org.ies.wargame.domain.model.User

interface AuthRepository {
    fun login(email: String, password: String): Boolean
    fun register(email: String, name: String, password: String): User?
}
