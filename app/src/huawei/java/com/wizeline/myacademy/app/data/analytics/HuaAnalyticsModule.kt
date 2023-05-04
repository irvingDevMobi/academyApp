package com.wizeline.myacademy.app.data.analytics

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HuaAnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        @ApplicationContext
        context: Context
    ): AnalyticsRepository = HuaAnalyticsRepository(context)
}
