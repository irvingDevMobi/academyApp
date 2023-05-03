package com.wizeline.myacademy.app.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.myacademy.app.domain.login.LoginError
import com.wizeline.myacademy.app.domain.login.LoginUseCase
import com.wizeline.myacademy.app.utils.hasCapitalLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> get() = _uiState

    var user by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateUser(
        user: String
    ) {
        this.user = user
        processInputs(this.user, this.password)
    }

    fun updatePassword(
        password: String
    ) {
        this.password = password
        processInputs(this.user, this.password)
    }

    private fun processInputs(
        user: String,
        password: String
    ) {
        val error = if (password.isNotBlank()) {
            if (password.length < MIN_PASSWORD_LENGTH) LoginUiError.NOT_MIN_PASSWORD_LENGTH
            else if (!password.hasCapitalLetter()) LoginUiError.MISSING_CAPITAL_LETTER
            else null
        } else null
        _uiState.update { currentUiState ->
            currentUiState.copy(
                buttonEnable = user.trim().isNotBlank() && error == null,
                error = error,
            )
        }
    }

    fun login(
        user: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showLoader = true)
            }
            val loginState = loginUseCase(user, password)
            _uiState.update { currentUiState ->
                currentUiState.copy(
                    showLoader = false,
                    successLogin = loginState.wasSuccess,
                    error = when (loginState.error) {
                        LoginError.WRONG_INFORMATION -> LoginUiError.WRONG_INFO
                        LoginError.USER_BLOCKED -> LoginUiError.USER_BLOCKED
                        else -> null
                    }
                )
            }
        }
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }
}
