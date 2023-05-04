package dev.irving.basecode.ui.general

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Qualifies a dependency as a member of the Data layer.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataLayer

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @Provides
    @Singleton
    @DataLayer
    fun provideDataCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    @DataLayer
    fun provideDataScope(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(dispatcher)
    }
}
