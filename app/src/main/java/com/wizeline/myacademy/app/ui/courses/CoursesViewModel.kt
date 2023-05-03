package com.wizeline.myacademy.app.ui.courses

import androidx.lifecycle.ViewModel
import com.wizeline.myacademy.app.data.courses.CoursesRepository
import com.wizeline.myacademy.app.domain.users.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository
) : ViewModel() {

    fun fetchCourses(userType: UserType) {

    }
}
