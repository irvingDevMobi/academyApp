package dev.irving.basecode.data.course

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.irving.basecode.ui.general.DataLayer
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
object CoursesDataSource {

    @Provides
    fun provideRemoteSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): CourseOnlyReadSource = CoursesFirebaseSource(dispatcher)
}
