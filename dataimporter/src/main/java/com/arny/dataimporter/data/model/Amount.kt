package com.arny.dataimporter.data.model

data class Amount(
    val id: Long,
    val addressId: Long,
    val meterId: Long,
    val tariffId: Long,
    val year: Int,
    val month: Int,
    val value: String,
    val comment: String? = null
)
