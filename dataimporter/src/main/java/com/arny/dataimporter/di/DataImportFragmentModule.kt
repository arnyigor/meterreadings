package com.arny.dataimporter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arny.dataimporter.data.files.FilesRepository
import com.arny.dataimporter.data.xml.DataImporter
import com.arny.dataimporter.presentation.DataImportFragment
import com.arny.dataimporter.presentation.DataImportViewModel
import com.arny.metersreading.core.di.FragmentScope
import com.arny.metersreading.core.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        DataImportFragmentModule.ProvideViewModel::class
    ]
)
interface DataImportFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    fun contributeFragmentInjector(): DataImportFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(DataImportViewModel::class)
        fun provideHomeViewModel(
            dataImporter: DataImporter,
            filesRepository: FilesRepository
        ): ViewModel = DataImportViewModel(dataImporter, filesRepository)
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideHomeViewModel(
            factory: ViewModelProvider.Factory,
            target: DataImportFragment
        ) = ViewModelProvider(target, factory).get(DataImportViewModel::class.java)
    }
}
