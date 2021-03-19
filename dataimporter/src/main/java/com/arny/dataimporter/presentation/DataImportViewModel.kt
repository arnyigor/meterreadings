package com.arny.dataimporter.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arny.androidutils.livedata.mutableLiveData
import com.arny.dataimporter.data.files.FilesRepository
import com.arny.dataimporter.data.xml.DataImporter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataImportViewModel(
    private val dataImporter: DataImporter,
    private val filesRepository: FilesRepository
) : ViewModel() {
    val data = mutableLiveData("")

    fun readFile(uri: Uri?) {
        viewModelScope.launch {
            data.value = withContext(Dispatchers.IO) {
                try {
                    val content = readContentFromUri(uri)
                    dataImporter.importData(content)
                } catch (e: Exception) {
                    e.printStackTrace()
                    e.message
                }
            }
        }
    }

    private fun readContentFromUri(uri: Uri?): String? {
        return filesRepository.getFile(uri)?.readText()
    }
}
