package com.arny.metersreading.core.models.meter

import com.arny.metersreading.core.models.base.Params

data class Tariff(
    val id: Long,
    val title: String,
    val measure: String,
    val params: Params? = null,
    val comment: String? = null
)
