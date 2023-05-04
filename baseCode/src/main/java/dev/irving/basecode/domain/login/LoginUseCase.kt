package dev.irving.basecode.domain.login

import dev.irving.basecode.data.login.LoginRepository
import dev.irving.basecode.data.session.SessionRepository
import dev.irving.basecode.domain.session.Session
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
