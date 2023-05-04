package dev.irving.basecode.ui.login

data class LoginUiState(
    val successLogin: Boolean = false,
    val showLoader: Boolean = false,
    val buttonEnable: Boolean = false,
    val error: LoginUiError? = null
)
