package com.arny.dataimporter.data.model

data class Meter(
    val id: Long,
    val title: String,
    val addressId: Long,
    val icon: String? = null,
    val color: Int? = null,
    val model: String? = null,
    val number: String? = null,
    val expDate: String? = null,
)