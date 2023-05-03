package com.wizeline.myacademy.app.data.login

import com.wizeline.myacademy.app.domain.login.LoginResult
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginRemoteSource: LoginRemoteSource
) {

    suspend fun login(user: String, password: String): LoginResult =
        loginRemoteSource.login(user, password)
}
