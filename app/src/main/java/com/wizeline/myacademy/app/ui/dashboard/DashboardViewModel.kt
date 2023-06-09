package com.wizeline.myacademy.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.myacademy.app.data.courses.CoursesRepository
import com.wizeline.myacademy.app.domain.users.GetCurrentUserUseCase
import com.wizeline.myacademy.app.ui.courses.CourseUi
import com.wizeline.myacademy.app.ui.courses.CoursesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val coursesRepository: CoursesRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState>
        get() = combine(
            getCurrentUserUseCase.userFlow,
            coursesRepository.coursesFlow
        ) { user, courses ->
            uiState.value.copy(
                userType = user.type,
                isLoading = false,
                coursesUiState = CoursesUiState(
                    courses = courses.map { course ->
                        CourseUi(
                            id = course.id,
                            name = course.name,
                            description = course.description
                        )
                    }
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardUiState()
        )

    init {
        fetchCourses()
    }

    fun fetchCourses() {
        viewModelScope.launch {
            getCurrentUserUseCase()
        }
    }
}
