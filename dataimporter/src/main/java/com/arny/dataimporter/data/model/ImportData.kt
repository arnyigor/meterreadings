package com.arny.dataimporter.data.model

import com.arny.metersreading.core.models.meter.MeterAddress
import com.arny.metersreading.core.models.meter.Tariff

data class ImportData(val meterAddress: MeterAddress, val tariffs: List<Tariff>)
