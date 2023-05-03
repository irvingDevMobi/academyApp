package com.wizeline.myacademy.app.domain.login

import com.wizeline.myacademy.app.data.login.LoginRepository
import com.wizeline.myacademy.app.data.session.SessionRepository
import com.wizeline.myacademy.app.domain.session.Session
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user: String, password: String): LoginResult {
        val loginResult = loginRepository.login(user, password)
        if (loginResult.wasSuccess) {
            sessionRepository.save(
                Session(
                    token = loginResult.exchangeToken,
                    currentUserId = user
                )
            )
        }
        return loginResult
    }
}
