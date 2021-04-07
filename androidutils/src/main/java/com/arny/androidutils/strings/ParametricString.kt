package com.arny.androidutils.strings

import android.content.Context

class ParametricString(private val format: String, private vararg val params: Any?) :
    WrappedString {
    override fun toString(context: Context): String = String.format(format, *params)
}
