package com.arny.dataimporter.data.model
import com.arny.metersreading.core.models.Params

data class MeterAmount(
        val id: Long? = null,
        val addressId: Long? = null,
        val meterId: Long? = null,
        val tariffId: Long? = null,
        val dateTime: String? = null,
        val amount: String? = null,
        val params: Params? = null,
        val comment: String? = null
)
