package com.arny.androidutils.strings

import android.content.Context

class SimpleString(val string: String?) : WrappedString {
    override fun toString(context: Context): String? = string
}