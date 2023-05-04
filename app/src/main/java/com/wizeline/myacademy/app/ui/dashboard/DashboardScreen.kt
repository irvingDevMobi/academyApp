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
        dashboardUiState = uiState
    )
}

@Composable
fun DashboardView(
    dashboardUiState: DashboardUiState
) {
    if (dashboardUiState.isLoading) {
        LoadingScreen()
    } else {
        CoursesScreen(uiState = dashboardUiState.coursesUiState)
    }
}
