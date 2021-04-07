package com.arny.androidutils.strings

import android.content.Context

interface WrappedString {
    fun toString(context: Context): String?
}