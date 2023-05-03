package com.wizeline.myacademy.app.ui.courses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wizeline.myacademy.app.R
import com.wizeline.myacademy.app.ui.theme.AcademyTheme

@Composable
fun CoursesScreen(
    uiState: CoursesUiState,
    modifier: Modifier = Modifier,
    buttonClicked: (String) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(uiState.courses) { uiItem ->
            Card(
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = uiItem.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = uiItem.description)
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(
                        onClick = { buttonClicked(uiItem.id) },
                        modifier = modifier.align(Alignment.End),
                        enabled = !uiItem.enrolled
                    ) {
                        Text(text = stringResource(id = R.string.enroll))
                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CoursesScreenPrev() {
    AcademyTheme {
        CoursesScreen(
            uiState = CoursesUiState(
                listOf(
                    CourseUi(
                        id = "androidBasic",
                        name = "Android",
                        description = "A base course to can develop a mobile app",
                        enrolled = false
                    )
                )
            )
        )
    }
}
