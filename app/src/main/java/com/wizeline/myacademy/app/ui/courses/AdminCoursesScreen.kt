package com.wizeline.myacademy.app.ui.courses

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.myacademy.app.R
import com.wizeline.myacademy.app.ui.theme.AcademyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCoursesScreen(
    coursesUiState: CoursesUiState,
    addNewCourse: () -> Unit = {},
) {

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = addNewCourse,
                text = { Text(text = stringResource(id = R.string.add_new_course)) },
                icon = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            )
        }
    ) { contentPadding ->
        CoursesScreen(
            uiState = coursesUiState,
            modifier = Modifier.padding(contentPadding)
                .fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun AdminCoursesScreenPrev() {
    AcademyTheme {
        AdminCoursesScreen(
            CoursesUiState(listOf(
                CourseUi("id", "Android","Description"),
                CourseUi("id", "Android","Description"),
            ))
        )
    }
}