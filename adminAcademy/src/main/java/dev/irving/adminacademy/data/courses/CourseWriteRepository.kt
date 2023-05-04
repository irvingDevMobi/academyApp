package dev.irving.adminacademy.data.courses

import dev.irving.basecode.domain.courses.Course
import javax.inject.Inject

class CourseWriteRepository @Inject constructor(
    private val remoteSource: CoursesWriteSource
) {
    suspend fun create(course: Course) {
        remoteSource.create(course)
    }
}
