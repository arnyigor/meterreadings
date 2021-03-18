package com.arny.dataimporter.di

import com.arny.dataimporter.data.xml.DataImporter
import com.arny.dataimporter.data.xml.XmlImporterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataImporterModule {
    @Binds
    @Singleton
    fun bindsDataImporter(importer: XmlImporterImpl): DataImporter
}
