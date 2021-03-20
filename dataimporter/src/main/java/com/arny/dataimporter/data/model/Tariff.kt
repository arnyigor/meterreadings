package com.arny.dataimporter.data.model

data class Tariff(
    val id: Long,
    val title: String,
    val measure: String,
    val params: String,
    val comment: String? = null
)
