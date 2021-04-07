package com.arny.metersreading.core.models.meter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meter(
    val id: Long? = null,
    val title: String? = null,
    val addressId: Long? = null,
    val typeId: Long? = null,
    val companyId: Long? = null,
    val icon: String? = null,
    val color: Int? = null,
    val model: String? = null,
    val number: String? = null,
    val expDate: String? = null,
) : Parcelable
