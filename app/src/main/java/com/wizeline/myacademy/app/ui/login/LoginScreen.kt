package com.wizeline.myacademy.app.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wizeline.myacademy.app.R
import com.wizeline.myacademy.app.ui.theme.AcademyTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier,
    goToDashboard: () -> Unit = {}
) {
    val uiState by loginViewModel.uiState.collectAsState()

    LoginView(
        user = loginViewModel.user,
        password = loginViewModel.password,
        modifier = modifier,
        showLoader = uiState.showLoader,
        isButtonEnable = uiState.buttonEnable,
        error = when (uiState.error) {
            LoginUiError.USER_BLOCKED -> stringResource(id = R.string.user_bloecked)
            LoginUiError.WRONG_INFO -> stringResource(id = R.string.wrong_user_info)
            LoginUiError.NOT_MIN_PASSWORD_LENGTH -> stringResource(id = R.string.lenght_password_error)
            LoginUiError.MISSING_CAPITAL_LETTER -> stringResource(id = R.string.missing_capital_letter_error)
            else -> ""
        },
        onUserChanged = { loginViewModel.updateUser(it) },
        onPasswordChanged = { loginViewModel.updatePassword(it) },
        validateUserInfo = { user, password -> loginViewModel.login(user, password) }
    )
    LaunchedEffect(key1 = uiState.successLogin) {
        if (uiState.successLogin) goToDashboard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    user: String,
    password: String,
    modifier: Modifier = Modifier,
    showLoader: Boolean = false,
    isButtonEnable: Boolean = false,
    error: String = "",
    onUserChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    validateUserInfo: (String, String) -> Unit = { _, _ -> }
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        OutlinedTextField(
            value = user,
            onValueChange = onUserChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(stringResource(id = R.string.user_label))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            enabled = !showLoader
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(text = stringResource(id = R.string.password_label))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { validateUserInfo(user, password) }
            ),
            enabled = !showLoader
        )
        Text(text = error, color = Color.Red)
        if (showLoader) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Button(
                modifier = modifier.align(Alignment.End),
                onClick = { validateUserInfo(user, password) },
                enabled = isButtonEnable
            ) {
                Text(
                    text = stringResource(id = R.string.login_label),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    AcademyTheme {
        LoginView(user = "irvingDev", password = "qwerty123")
    }
}
