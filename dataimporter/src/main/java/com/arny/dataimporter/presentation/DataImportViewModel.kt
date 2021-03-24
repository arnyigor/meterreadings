package com.arny.dataimporter.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arny.androidutils.livedata.mutableLiveData
import com.arny.dataimporter.R
import com.arny.dataimporter.data.files.FilesRepository
import com.arny.dataimporter.data.xml.DataImporter
import com.arny.metersreading.core.models.BussinessException
import com.arny.metersreading.core.models.DataResult
import com.arny.metersreading.core.models.tryGetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataImportViewModel(
    private val dataImporter: DataImporter,
    private val filesRepository: FilesRepository
) : ViewModel() {
    val data = mutableLiveData<DataResult<String>?>(DataResult.NOTHING)

    fun readFile(uri: Uri?) {
        viewModelScope.launch {
            data.value = withContext(Dispatchers.IO) {
                tryGetResult{
                    val content = readContentFromUri(uri)
                    val importData = dataImporter.importData(content)
                    DataResult.SUCCESS(importData)
                }
            }
        }
    }

    private fun readContentFromUri(uri: Uri?): String {
        return uri?.let { filesRepository.getFile(it)?.readText() }
            ?: throw BussinessException(messageRes = R.string.empty_uri)
    }
}
