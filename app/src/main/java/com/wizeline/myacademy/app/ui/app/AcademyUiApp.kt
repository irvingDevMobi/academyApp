package com.wizeline.myacademy.app.ui.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wizeline.myacademy.app.ui.dashboard.DashboardScreen
import dev.irving.basecode.app.AcademyUiAppViewModel
import dev.irving.basecode.app.Destination
import dev.irving.basecode.ui.login.LoginScreen
import dev.irving.basecode.ui.session.SessionCheckScreen

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
            DashboardScreen(dashboardViewModel = hiltViewModel())
        }
    }
}

