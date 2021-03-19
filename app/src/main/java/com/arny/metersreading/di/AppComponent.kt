package com.arny.metersreading.di

import com.arny.dataimporter.di.DataImporterModule
import com.arny.metersreading.MetersReadingApp
import com.arny.metersreading.presentation.di.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataImporterModule::class,
        UiModule::class,
    ]
)
interface AppComponent : AndroidInjector<MetersReadingApp> {
    override fun inject(application: MetersReadingApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MetersReadingApp): Builder

        fun build(): AppComponent
    }
}
