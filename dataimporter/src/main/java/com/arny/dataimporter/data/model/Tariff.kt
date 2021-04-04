package com.arny.dataimporter.data.model

import com.arny.metersreading.core.models.Params

data class Tariff(
    val id: Long,
    val title: String,
    val measure: String,
    val params: Params? = null,
    val comment: String? = null
)
