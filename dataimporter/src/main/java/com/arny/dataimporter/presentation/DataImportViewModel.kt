package com.arny.dataimporter.presentation

import androidx.lifecycle.ViewModel
import com.arny.androidutils.mutableLiveData
import com.arny.dataimporter.data.xml.DataImporter

class DataImportViewModel(dataImporter: DataImporter) : ViewModel() {
    val data = mutableLiveData("")
    init {
        data.value = dataImporter.importData("<data><phone><company>Samsung</company></phone></data>")
    }
}
