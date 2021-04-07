package com.arny.androidutils.strings

import android.content.Context
import androidx.annotation.StringRes

class ResourceString(@StringRes val resString: Int) : WrappedString {

    override fun toString(context: Context): String = context.getString(resString)
}