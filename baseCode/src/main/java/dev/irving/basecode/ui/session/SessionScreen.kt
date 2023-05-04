package dev.irving.basecode.ui.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.irving.basecode.app.AcademyUiAppViewModel
import dev.irving.basecode.app.Destination
import dev.irving.basecode.ui.general.LoadingScreen

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
