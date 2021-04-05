package com.arny.metersreading.meteredit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arny.metersreading.core.di.FragmentScope
import com.arny.metersreading.core.viewmodel.ViewModelKey
import com.arny.metersreading.meteredit.presentation.edit.MetersEditFragment
import com.arny.metersreading.meteredit.presentation.edit.MetersEditViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        MetersEditFragmentModule.ProvideViewModel::class
    ]
)
interface MetersEditFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    fun contributeFragmentInjector(): MetersEditFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(MetersEditViewModel::class)
        fun provideHomeViewModel(
        ): ViewModel = MetersEditViewModel()
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideHomeViewModel(
            factory: ViewModelProvider.Factory,
            target: MetersEditFragment
        ) = ViewModelProvider(target, factory).get(MetersEditViewModel::class.java)
    }
}
