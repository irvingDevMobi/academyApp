package dev.irving.basecode.data.login

import dev.irving.basecode.domain.login.LoginResult


interface LoginRemoteSource {
    suspend fun login(user: String, password: String): LoginResult
}
