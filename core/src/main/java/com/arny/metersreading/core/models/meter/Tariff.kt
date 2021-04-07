package com.arny.metersreading.core.models.meter

import android.os.Parcelable
import com.arny.metersreading.core.models.base.Params
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tariff(
    val id: Long,
    val title: String,
    val measure: String,
    val params: Params? = null,
    val comment: String? = null
) : Parcelable
