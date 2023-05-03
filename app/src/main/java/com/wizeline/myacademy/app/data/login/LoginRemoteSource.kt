package com.wizeline.myacademy.app.data.login

import com.wizeline.myacademy.app.domain.login.LoginResult

interface LoginRemoteSource {
    suspend fun login(user: String, password: String): LoginResult
}
