package com.arny.metersreading.presentation.di

import com.arny.dataimporter.di.DataImportFragmentModule
import com.arny.metersreading.core.di.ActivityScope
import com.arny.metersreading.meteredit.di.MetersEditFragmentModule
import com.arny.metersreading.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            HomeFragmentModule::class,
            DataImportFragmentModule::class,
            MetersEditFragmentModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}
