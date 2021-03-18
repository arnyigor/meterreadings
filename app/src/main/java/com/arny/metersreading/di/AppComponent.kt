package com.arny.metersreading.di

import com.arny.metersreading.MetersReadingApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
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
