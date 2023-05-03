package com.wizeline.myacademy.app.data.courses

import com.wizeline.myacademy.app.domain.courses.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoursesRepository @Inject constructor(
    private val remoteSource: CoursesRemoteSource
) {
    val coursesFlow: Flow<List<Course>> get() = remoteSource.coursesFlow

    suspend fun create(course: Course) {
        remoteSource.create(course)
    }
}
