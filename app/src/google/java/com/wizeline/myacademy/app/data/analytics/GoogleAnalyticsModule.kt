package com.wizeline.myacademy.app.data.analytics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleAnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsRepository(): AnalyticsRepository = GoogleAnalyticsRepository()
}
