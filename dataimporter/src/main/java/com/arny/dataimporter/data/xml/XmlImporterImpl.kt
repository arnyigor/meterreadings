package com.arny.dataimporter.data.xml

import android.content.Context
import com.arny.dataimporter.R
import org.xmlpull.v1.XmlPullParser
import javax.inject.Inject

class XmlImporterImpl @Inject constructor(
    private val context: Context
) : DataImporter {
    override fun importData(): String {
        val parser = context.resources.getXml(R.xml.data)
        val toString = StringBuilder().apply {
            while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                if (parser.eventType == XmlPullParser.START_TAG
                    && parser.name.equals("DATA")
                ) {
                    for (i in 0 until parser.attributeCount) {
                        val attributeName = parser.getAttributeName(i)
                        val value = parser.getAttributeValue(i)
                    }
                }
                parser.next();
            }
        }.toString()
        return "Импорт данных $toString"
    }
}