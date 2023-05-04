package dev.irving.adminacademy.data.courses

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.irving.basecode.ui.general.DataLayer
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object CourseDataModule {

    @Provides
    fun provideCourseWriteSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): CoursesWriteSource = CourseWriteFirebaseSource(dispatcher)
}
