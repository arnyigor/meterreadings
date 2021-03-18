package com.arny.metersreading.di

import android.content.Context
import com.arny.metersreading.MetersReadingApp
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {
    @Binds
    abstract fun provideContext(application: MetersReadingApp): Context
}
