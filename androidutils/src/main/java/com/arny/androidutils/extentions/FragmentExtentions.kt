package com.arny.androidutils.extentions

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun <T> Fragment.requestPermission(
    resultLauncher: ActivityResultLauncher<T>,
    permission: String,
    input: T,
    checkPermissionOk: () -> Unit = {}
) {
    when {
        ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED -> {
            checkPermissionOk()
        }
        shouldShowRequestPermissionRationale(permission) -> {
            resultLauncher.launch(input)
        }
        else -> {
            resultLauncher.launch(input)
        }
    }
}