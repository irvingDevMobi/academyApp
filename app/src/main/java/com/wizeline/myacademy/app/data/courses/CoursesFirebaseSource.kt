package com.wizeline.myacademy.app.data.courses

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.wizeline.myacademy.app.domain.courses.Course
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import timber.log.Timber

class CoursesFirebaseSource(
    private val dispatcher: CoroutineDispatcher
) : CoursesRemoteSource {

    private val _courseFlow = MutableStateFlow<List<Course>>(listOf())

    override val coursesFlow: StateFlow<List<Course>>
        get() = _courseFlow

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val courses = snapshot.children.map { child ->
                    val courseResponse = child.getValue<CourseResponse>() ?: CourseResponse()
                    Course(
                        snapshot.key ?: "",
                        courseResponse.name,
                        courseResponse.description,
                        courseResponse.lecturer
                    )
                }
                Timber.d("Irving Sending courses $courses")
                _courseFlow.update {
                    courses
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.e(error.toException())
            }

        }
        val coursesRef = Firebase.database.getReference("courses")
        coursesRef.addValueEventListener(eventListener)
    }

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
