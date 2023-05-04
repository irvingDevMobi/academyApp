package dev.irving.adminacademy.data.courses

import dev.irving.basecode.domain.courses.Course

interface CoursesWriteSource {
    suspend fun create(course: Course)
}
