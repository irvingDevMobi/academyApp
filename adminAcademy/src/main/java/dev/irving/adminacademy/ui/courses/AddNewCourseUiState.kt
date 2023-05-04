package dev.irving.adminacademy.ui.courses

data class AddNewCourseUiState(
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val saved: Boolean = false
)
