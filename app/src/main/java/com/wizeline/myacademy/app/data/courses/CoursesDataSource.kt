package com.wizeline.myacademy.app.data.courses

import com.wizeline.myacademy.app.ui.general.DataLayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object CoursesDataSource {

    @Provides
    fun provideRemoteSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): CoursesRemoteSource = CoursesFirebaseSource(dispatcher)
}
