package com.arny.dataimporter.data.model

data class MeterAmount(
        val id: Long? = null,
        val addressId: Long? = null,
        val meterId: Long? = null,
        val tariffId: Long? = null,
        val year: Int,
        val month: Int,
        val value: String,
        val comment: String? = null
)
