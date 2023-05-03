package com.wizeline.myacademy.app.data.user

import com.wizeline.myacademy.app.ui.general.DataLayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object UserDataModule {

    @Provides
    fun provideRemoteSource(
        @DataLayer
        dispatcher: CoroutineDispatcher
    ): UserRemoteSource = UserFirebaseSource(dispatcher)
}
