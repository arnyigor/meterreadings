package com.arny.metersreading.core.models

import android.content.Context
import androidx.annotation.StringRes

sealed class DataResult<out T : Any> {
    object NOTHING : DataResult<Nothing>()
    class PROGRESS(
        val message: String? = null,
        @StringRes val messageRes: Int? = null
    ) : DataResult<Nothing>(){
        fun toString(context: Context): String? {
            return messageRes?.let { context.getString(it) } ?: message
        }
    }

    class SUCCESS<out T : Any>(val data: T) : DataResult<T>()
    class ERROR(val exception: BussinessException) : DataResult<Nothing>()
}

fun <T : Any> tryGetResult(result: () -> DataResult<T>): DataResult<T> {
    return try {
        result()
    } catch (e: Exception) {
        when (e) {
            is BussinessException -> DataResult.ERROR(e)
            else -> DataResult.ERROR(BussinessException(e))
        }
    }
}