package com.arny.metersreading.meteredit.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arny.androidutils.mutableLiveData
import com.arny.androidutils.strings.SimpleString
import com.arny.androidutils.strings.WrappedString
import com.arny.metersreading.core.models.meter.Meter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MetersEditViewModel : ViewModel() {

    private var meter: Meter? = null

    val uiMeter = mutableLiveData<Meter?>(null)
    val toast = mutableLiveData<WrappedString?>(null)
    val loading = mutableLiveData(false)
    val save = mutableLiveData(false)

    fun saveData() {
        viewModelScope.launch {
            toast.value = SimpleString("Сохраняем...")
            loading.value = true
            delay(3000)
            loading.value = false
            toast.value = SimpleString("Сохранено")
            delay(1000)
            save.value = true
        }
    }

    fun setMeter(meter: Meter?) {
        this.meter = meter
        uiMeter.value = meter
    }
}