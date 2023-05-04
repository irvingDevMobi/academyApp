package com.wizeline.myacademy.app.ui.dashboard

import dev.irving.basecode.domain.users.UserType
import dev.irving.basecode.ui.courses.CoursesUiState

data class DashboardUiState(
    val isLoading: Boolean = true,
    val userType: UserType? = null,
    val coursesUiState: CoursesUiState = CoursesUiState(listOf())
)
