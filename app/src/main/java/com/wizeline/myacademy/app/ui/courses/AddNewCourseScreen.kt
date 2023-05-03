package com.wizeline.myacademy.app.ui.courses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.wizeline.myacademy.app.R
import com.wizeline.myacademy.app.domain.courses.Course


@Composable
fun AddNewCourseScreen(
    addNewCourseViewModel: AddNewCourseViewModel,
    modifier: Modifier = Modifier,
    goBack: () -> Unit
) {
    val uiState by addNewCourseViewModel.uiState.collectAsState()

    AddNewCourseView(
        id = addNewCourseViewModel.id,
        name = addNewCourseViewModel.name,
        description = addNewCourseViewModel.description,
        lecturer = addNewCourseViewModel.lecturer,
        modifier = modifier,
        onIdChanged = { addNewCourseViewModel.updateId(it) },
        onDescriptionChanged = { addNewCourseViewModel.updateDescription(it) },
        onNameChanged = { addNewCourseViewModel.updateName(it) },
        onLecturerChanged = { addNewCourseViewModel.updateLecturer(it) },
        showLoader = uiState.isLoading,
        isSaveEnabled = uiState.isButtonEnabled,
        onSavedClicked = { addNewCourseViewModel.save(it) }
    )
    LaunchedEffect(uiState.saved) {
        if (uiState.saved) goBack()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCourseView(
    id: String,
    name: String,
    description: String,
    lecturer: String,
    modifier: Modifier = Modifier,
    showLoader: Boolean = false,
    isSaveEnabled: Boolean = false,
    onIdChanged: (String) -> Unit = { },
    onNameChanged: (String) -> Unit = { },
    onDescriptionChanged: (String) -> Unit = { },
    onLecturerChanged: (String) -> Unit = { },
    onSavedClicked: (Course) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        OutlinedTextField(
            value = id,
            onValueChange = onIdChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.course_id)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            enabled = !showLoader
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.course_name)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            enabled = !showLoader
        )
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.course_description)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            enabled = !showLoader
        )
        OutlinedTextField(
            value = lecturer,
            onValueChange = onLecturerChanged,
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.course_lecturer)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            enabled = !showLoader
        )
        Button(
            onClick = { onSavedClicked(Course(id = id, name, description, lecturer)) },
            modifier = modifier.align(Alignment.End),
            enabled = isSaveEnabled
        ) {
            Text(text = stringResource(id = R.string.create_course))

        }
    }

}
