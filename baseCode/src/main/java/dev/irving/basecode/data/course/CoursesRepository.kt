package dev.irving.basecode.data.course

import dev.irving.basecode.domain.courses.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoursesRepository @Inject constructor(
    private val remoteSource: CourseOnlyReadSource
) {
    val coursesFlow: Flow<List<Course>> get() = remoteSource.coursesFlow

}
