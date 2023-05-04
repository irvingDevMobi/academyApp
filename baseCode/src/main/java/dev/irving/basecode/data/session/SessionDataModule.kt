package dev.irving.basecode.data.session

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.irving.basecode.ui.general.DataLayer
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionDataModule {
    @Provides
    @Singleton
    fun provideLocalSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): SessionLocalSource = SessionFakeSource(dispatcher)
}
