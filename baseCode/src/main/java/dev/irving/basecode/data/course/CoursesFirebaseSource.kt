package dev.irving.basecode.data.course

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class CoursesFirebaseSource(
    private val dispatcher: CoroutineDispatcher
) : CourseOnlyReadSource {

    private val _courseFlow =
        MutableStateFlow<List<dev.irving.basecode.domain.courses.Course>>(listOf())

    override val coursesFlow: StateFlow<List<dev.irving.basecode.domain.courses.Course>>
        get() = _courseFlow

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val courses = snapshot.children.map { child ->
                    val courseResponse = child.getValue<CourseResponse>() ?: CourseResponse()
                    dev.irving.basecode.domain.courses.Course(
                        child.key ?: "",
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


}
