package com.arny.metersreading.core.models.meter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeterCompany(val id: Long? = null, val title: String? = null) : Parcelable
