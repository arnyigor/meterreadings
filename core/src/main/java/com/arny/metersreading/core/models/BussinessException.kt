package com.arny.metersreading.core.models

import android.content.Context
import androidx.annotation.StringRes

data class BussinessException(
    override val message: String? = null,
    @StringRes val messageRes: Int? = null
) : Exception(message) {
    constructor(e: Exception) : this(e.message, null)

    fun toString(context: Context): String? {
        return messageRes?.let { context.getString(it) } ?: message
    }
}