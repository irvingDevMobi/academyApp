package dev.irving.basecode.domain.login

data class LoginResult(
    val wasSuccess: Boolean,
    val exchangeToken: String = "",
    val error: LoginError? = null
)
