package com.arny.metersreading.presentation.di

import com.arny.metersreading.di.ActivityScope
import com.arny.metersreading.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}
