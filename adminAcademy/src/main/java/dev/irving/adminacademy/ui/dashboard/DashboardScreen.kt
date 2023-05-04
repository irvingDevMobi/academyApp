package dev.irving.adminacademy.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.irving.basecode.domain.users.UserType
import dev.irving.adminacademy.ui.admin.AdminCoursesScreen
import dev.irving.basecode.ui.courses.CoursesScreen
import dev.irving.basecode.ui.general.LoadingScreen

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
