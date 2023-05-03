package com.wizeline.myacademy.app.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wizeline.myacademy.app.ui.courses.AddNewCourseScreen
import com.wizeline.myacademy.app.ui.dashboard.DashboardScreen
import com.wizeline.myacademy.app.ui.general.LoadingScreen
import com.wizeline.myacademy.app.ui.login.LoginScreen

@Composable
fun AcademyUiApp(
    appViewModel: AcademyUiAppViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destination.SESSION_CHECK.name
    ) {
        composable(route = Destination.SESSION_CHECK.name) {
            SessionCheckScreen(appViewModel = appViewModel) { nextDestination ->
                navController.navigate(nextDestination.name) {
                    popUpTo(Destination.SESSION_CHECK.name) { inclusive = true }
                }
            }
        }
        composable(route = Destination.LOGIN.name) {
            LoginScreen(
                loginViewModel = hiltViewModel()
            ) {
                navController.navigate(Destination.DASHBOARD.name) {
                    popUpTo(Destination.LOGIN.name) { inclusive = true }
                }
            }
        }
        composable(route = Destination.DASHBOARD.name) {
            DashboardScreen(dashboardViewModel = hiltViewModel()) {
                navController.navigate(Destination.ADD_NEW_COURSE.name)
            }
        }
        composable(route = Destination.ADD_NEW_COURSE.name) {
            AddNewCourseScreen(addNewCourseViewModel = hiltViewModel()) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun SessionCheckScreen(
    appViewModel: AcademyUiAppViewModel,
    goToNextScreen: (Destination) -> Unit
) {
    LoadingScreen()
    val sessionUiState by appViewModel.academyUiAppState.collectAsState()

    SessionCheckView(
        nextDestination = sessionUiState.nextDestination,
        goToNextScreen = goToNextScreen
    )
}

@Composable
fun SessionCheckView(
    nextDestination: Destination,
    goToNextScreen: (Destination) -> Unit
) {
    LaunchedEffect(nextDestination) {
        if (nextDestination != Destination.NOTHING) {
            goToNextScreen(nextDestination)
        }
    }
}
