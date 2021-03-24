package com.arny.dataimporter.data.files

import android.net.Uri
import java.io.File

interface FilesRepository {
    fun getFile(uri: Uri): File?
}
