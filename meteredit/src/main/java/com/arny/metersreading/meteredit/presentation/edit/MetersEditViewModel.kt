package com.arny.metersreading.meteredit.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arny.androidutils.mutableLiveData
import com.arny.androidutils.strings.SimpleString
import com.arny.androidutils.strings.WrappedString
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MetersEditViewModel : ViewModel() {

    val toast = mutableLiveData<WrappedString?>(null)
    val loading = mutableLiveData(false)
    val save = mutableLiveData(false)

    fun saveData() {
        viewModelScope.launch {
            toast.value = SimpleString("Сохраняем...")
            loading.value = true
            delay(3000)
            loading.value = false
            save.value = true
        }
    }
}