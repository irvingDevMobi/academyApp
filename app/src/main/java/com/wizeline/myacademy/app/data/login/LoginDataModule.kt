package com.wizeline.myacademy.app.data.login

import com.wizeline.myacademy.app.ui.general.DataLayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object LoginDataModule {

    @Provides
    fun provideRemoteSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): LoginRemoteSource = LoginFakeSource(dispatcher)
}
