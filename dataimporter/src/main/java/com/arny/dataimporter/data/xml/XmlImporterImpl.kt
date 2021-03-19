package com.arny.dataimporter.data.xml

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.inject.Inject


class XmlImporterImpl @Inject constructor(
    private val context: Context
) : DataImporter {
    override fun importData(data: String?): String {
        val parser = prepareXpp(data)
        val toString = StringBuilder().apply {
            while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                when (parser.eventType) {
                    XmlPullParser.START_TAG  -> {
                        append(parser.name)
                        append("\n")
                        for (i in 0 until parser.attributeCount) {
                            val attributeName = parser.getAttributeName(i)
                            val value = parser.getAttributeValue(i)
                            append("$attributeName=$value")
                        }
                        append("\n")
                    }
                    XmlPullParser.TEXT -> {
                        append(parser.text)
                    }
                }
                parser.next()
            }
        }.toString()
        return "Импорт данных $toString"
    }

    @Throws(XmlPullParserException::class)
    fun prepareXpp(s: String?): XmlPullParser {
        // получаем фабрику
        val factory = XmlPullParserFactory.newInstance()
        // включаем поддержку namespace (по умолчанию выключена)
        factory.isNamespaceAware = true
        // создаем парсер
        val xpp = factory.newPullParser()
        // даем парсеру на вход Reader
        xpp.setInput(StringReader(s))
        return xpp
    }
}