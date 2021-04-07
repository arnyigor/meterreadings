package com.arny.metersreading.core.models.meter
import android.os.Parcelable
import com.arny.metersreading.core.models.base.Params
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeterAmount(
        val id: Long? = null,
        val addressId: Long? = null,
        val meterId: Long? = null,
        val tariffId: Long? = null,
        val dateTime: String? = null,
        val data: Params? = null,
        val tariff: Tariff? = null,
        val comment: String? = null
):Parcelable
