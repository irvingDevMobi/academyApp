package dev.irving.basecode.ui.courses

data class CoursesUiState(
    val courses: List<CourseUi>
)

data class CourseUi(
    val id: String,
    val name: String = "",
    val description: String = "",
    val lecturer: String = "",
    val enrolled: Boolean = false
)
