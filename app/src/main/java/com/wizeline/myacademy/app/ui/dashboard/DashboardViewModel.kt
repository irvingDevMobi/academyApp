package com.wizeline.myacademy.app.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.myacademy.app.data.analytics.AnalyticsEvents
import com.wizeline.myacademy.app.data.analytics.AnalyticsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.irving.basecode.data.course.CoursesRepository
import dev.irving.basecode.domain.users.GetCurrentUserUseCase
import dev.irving.basecode.ui.courses.CourseUi
import dev.irving.basecode.ui.courses.CoursesUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    coursesRepository: CoursesRepository,
    private val analyticsRepository: AnalyticsRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        getCurrentUserUseCase.userFlow,
        coursesRepository.coursesFlow
    ) { user, courses ->
        DashboardUiState(
            userId = user.id,
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

    fun joinCourse(courseId: String) {
        analyticsRepository.logEvent(
            AnalyticsEvents.COURSE_INTEREST,
            Bundle().apply {
                putString(AnalyticsEvents.PARAM_COURSE_ID, courseId)
                putString(AnalyticsEvents.PARAM_USER_ID, uiState.value.userId)
            }
        )
    }
}
