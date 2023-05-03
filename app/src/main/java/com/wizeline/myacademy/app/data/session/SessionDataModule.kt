package com.wizeline.myacademy.app.data.session

import com.wizeline.myacademy.app.ui.general.DataLayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
