package com.wizeline.myacademy.app.ui.courses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.myacademy.app.data.courses.CoursesRepository
import com.wizeline.myacademy.app.domain.courses.Course
import com.wizeline.myacademy.app.utils.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewCourseViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddNewCourseUiState())
    val uiState: StateFlow<AddNewCourseUiState> get() = _uiState

    var id by mutableStateOf("")
        private set
    var name by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var lecturer by mutableStateOf("")
        private set

    fun updateId(
        id: String
    ) {
        this.id = id
        processInputs(this.id, this.name, this.description, this.lecturer)
    }

    fun updateName(
        name: String
    ) {
        this.name = name
        processInputs(this.id, this.name, this.description, this.lecturer)
    }

    fun updateDescription(
        description: String
    ) {
        this.description = description
        processInputs(this.id, this.name, this.description, this.lecturer)
    }

    fun updateLecturer(
        lecturer: String
    ) {
        this.lecturer = lecturer
        processInputs(this.id, this.name, this.description, this.lecturer)
    }

    private fun processInputs(
        id: String,
        name: String,
        description: String,
        lecturer: String
    ) {
        _uiState.update {
            it.copy(
                isButtonEnabled = Strings.noneIsEmpty(id, name, description, lecturer)
            )
        }
    }

    fun save(course: Course) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            coursesRepository.create(course)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    saved = true
                )
            }
        }
    }
}
