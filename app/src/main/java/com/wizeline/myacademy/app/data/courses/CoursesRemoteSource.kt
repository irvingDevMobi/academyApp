package com.wizeline.myacademy.app.data.courses

import com.wizeline.myacademy.app.domain.courses.Course
import kotlinx.coroutines.flow.StateFlow

interface CoursesRemoteSource {

    val coursesFlow: StateFlow<List<Course>>

    suspend fun create(course: Course)
}
