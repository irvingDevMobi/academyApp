package dev.irving.basecode.data.course

import dev.irving.basecode.domain.courses.Course
import kotlinx.coroutines.flow.StateFlow

interface CourseOnlyReadSource {
    val coursesFlow: StateFlow<List<Course>>
}
