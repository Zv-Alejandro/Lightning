package org.ies.wargame.data.repository

import org.ies.wargame.domain.model.User
import org.ies.wargame.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    private val users = mutableListOf<User>()

    override fun login(email: String, password: String): Boolean {
        return users.any { it.email == email }
    }

    override fun register(email: String, name: String, password: String): User? {
        val user = User(email, name)
        users.add(user)
        return user
    }
}
