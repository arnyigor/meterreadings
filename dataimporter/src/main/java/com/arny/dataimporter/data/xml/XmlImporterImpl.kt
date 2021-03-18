package com.arny.dataimporter.data.xml

import android.content.Context
import com.arny.dataimporter.R
import javax.inject.Inject

class XmlImporterImpl @Inject constructor(
  private val context: Context
) : DataImporter {
  override fun importData(): String {
     return "Импорт данных ${context.getString(R.string.test_data)}"
  }
}