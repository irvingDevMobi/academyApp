package dev.irving.adminacademy.data.courses

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.irving.basecode.data.course.CourseResponse
import dev.irving.basecode.domain.courses.Course
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CourseWriteFirebaseSource(
    private val dispatcher: CoroutineDispatcher
) : CoursesWriteSource{
    override suspend fun create(course: Course): Unit = withContext(dispatcher) {
        val coursesRef = Firebase.database.getReference("courses")
        coursesRef.child(course.id).setValue(
            CourseResponse(
                course.description,
                course.lecturer,
                course.name
            )
        )
    }
}
