package com.wizeline.myacademy.app.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.irving.basecode.ui.courses.CoursesScreen
import dev.irving.basecode.ui.general.LoadingScreen

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
) {
    val uiState by dashboardViewModel.uiState.collectAsStateWithLifecycle()
    DashboardView(
        dashboardUiState = uiState,
        joinButtonClicked = { dashboardViewModel.joinCourse(it) }
    )
}

@Composable
fun DashboardView(
    dashboardUiState: DashboardUiState,
    joinButtonClicked: (String) -> Unit
) {
    if (dashboardUiState.isLoading) {
        LoadingScreen()
    } else {
        CoursesScreen(
            uiState = dashboardUiState.coursesUiState,
            courseJoinClicked = joinButtonClicked
        )
    }
}
