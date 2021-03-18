package com.arny.metersreading.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arny.dataimporter.data.xml.DataImporter
import com.arny.metersreading.di.FragmentScope
import com.arny.metersreading.di.ViewModelKey
import com.arny.metersreading.presentation.home.HomeFragment
import com.arny.metersreading.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        HomeFragmentModule.ProvideViewModel::class
    ]
)
interface HomeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    fun contributeFragmentInjector(): HomeFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun provideHomeViewModel(
            dataImporter: DataImporter,
        ): ViewModel = HomeViewModel(dataImporter)
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideHomeViewModel(
            factory: ViewModelProvider.Factory,
            target: HomeFragment
        ) = ViewModelProvider(target, factory).get(HomeViewModel::class.java)
    }
}