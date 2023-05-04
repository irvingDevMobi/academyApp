package dev.irving.basecode.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.irving.basecode.domain.session.GetDestinationWithSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcademyUiAppViewModel @Inject constructor(
    private val getDestinationWithSession: GetDestinationWithSession
) : ViewModel() {

    private val _academyUiAppState = MutableStateFlow(AcademyUiAppState())
    val academyUiAppState: StateFlow<AcademyUiAppState> get() = _academyUiAppState

    init {
        checkSession()
    }

    fun checkSession() {
        viewModelScope.launch {
            delay(100)
            _academyUiAppState.update {
                AcademyUiAppState(getDestinationWithSession())
            }
        }
    }
}
