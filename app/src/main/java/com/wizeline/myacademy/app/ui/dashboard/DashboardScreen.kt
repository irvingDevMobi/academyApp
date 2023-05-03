package com.wizeline.myacademy.app.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wizeline.myacademy.app.domain.users.UserType
import com.wizeline.myacademy.app.ui.courses.AdminCoursesScreen
import com.wizeline.myacademy.app.ui.courses.CoursesScreen
import com.wizeline.myacademy.app.ui.general.LoadingScreen

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
    goToAddNewCourse: () -> Unit
) {
    val uiState by dashboardViewModel.uiState.collectAsStateWithLifecycle()
    DashboardView(
        dashboardUiState = uiState,
        goToAddNewCourse = goToAddNewCourse
    )
}

@Composable
fun DashboardView(
    dashboardUiState: DashboardUiState,
    goToAddNewCourse: () -> Unit
) {
    if (dashboardUiState.isLoading) {
        LoadingScreen()
    } else {
        if (dashboardUiState.userType == UserType.ADMIN) {
            AdminCoursesScreen(
                coursesUiState = dashboardUiState.coursesUiState,
                addNewCourse = goToAddNewCourse
            )
        } else CoursesScreen(uiState = dashboardUiState.coursesUiState)
    }
}
