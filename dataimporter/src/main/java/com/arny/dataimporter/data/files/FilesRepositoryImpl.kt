package com.arny.dataimporter.data.files

import android.content.Context
import android.net.Uri
import com.arny.androidutils.files.FilePathUtils
import java.io.File
import javax.inject.Inject

class FilesRepositoryImpl @Inject constructor(
    private val context: Context
) : FilesRepository {
    override fun getFile(uri: Uri?): File? {
        return FilePathUtils.getPath(uri, context)?.let { File(it) }
    }
}
