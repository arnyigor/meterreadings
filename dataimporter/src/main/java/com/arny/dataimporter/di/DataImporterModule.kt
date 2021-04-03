package com.arny.dataimporter.di

import com.arny.dataimporter.data.files.FilesRepository
import com.arny.dataimporter.data.files.FilesRepositoryImpl
import com.arny.dataimporter.data.reader.ImportDataReader
import com.arny.dataimporter.data.reader.ImportDataReaderImpl
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

    @Binds
    @Singleton
    fun bindsDataReader(reader: ImportDataReaderImpl): ImportDataReader

    @Binds
    @Singleton
    fun bindsFilesRepository(repository: FilesRepositoryImpl): FilesRepository
}
