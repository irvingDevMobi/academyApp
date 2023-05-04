package dev.irving.basecode.data.login

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.irving.basecode.ui.general.DataLayer
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
