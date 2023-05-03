package com.wizeline.myacademy.app.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wizeline.myacademy.app.ui.theme.AcademyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademyTheme {
                AcademyUiApp()
            }
        }
    }
}
