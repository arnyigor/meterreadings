package com.arny.metersreading.presentation.home

import androidx.lifecycle.ViewModel
import com.arny.androidutils.mutableLiveData
import com.arny.dataimporter.data.xml.DataImporter

class HomeViewModel(private val dataImporter: DataImporter) : ViewModel() {

    val text = mutableLiveData("This is home Fragment")

    fun import() {
        text.value = dataImporter.importData()
    }
}
