package dev.irving.basecode.data.login

import dev.irving.basecode.domain.login.LoginError
import dev.irving.basecode.domain.login.LoginResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginFakeSource(
    private val dispatcher: CoroutineDispatcher
) : LoginRemoteSource {
    override suspend fun login(
        user: String,
        password: String
    ): LoginResult = withContext(dispatcher) {
        delay(1000)
        if (user == "blocked") LoginResult(false, error = LoginError.USER_BLOCKED)
        else if (users[user] == password) LoginResult(true, "validToken")
        else LoginResult(false, error = LoginError.WRONG_INFORMATION)
    }

    companion object {
        val users = mapOf(
            "irvingDev" to "Qwerty123",
            "menteeBaz" to "Pass123",
            "menteeEkt" to "Qazwsx789",
            "vio123" to "Admin123"
        )
    }
}
