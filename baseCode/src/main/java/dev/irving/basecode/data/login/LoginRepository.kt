package dev.irving.basecode.data.login

import dev.irving.basecode.domain.login.LoginResult
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginRemoteSource: LoginRemoteSource
) {

    suspend fun login(user: String, password: String): LoginResult =
        loginRemoteSource.login(user, password)
}
