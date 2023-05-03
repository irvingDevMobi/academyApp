package com.wizeline.myacademy.app.ui.dashboard

import com.wizeline.myacademy.app.domain.users.UserType
import com.wizeline.myacademy.app.ui.courses.CoursesUiState

data class DashboardUiState(
    val isLoading: Boolean = true,
    val userType: UserType? = null,
    val coursesUiState: CoursesUiState = CoursesUiState(listOf())
)
