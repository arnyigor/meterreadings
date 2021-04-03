package com.arny.dataimporter.data.xml

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.inject.Inject

class XmlImporterImpl @Inject constructor() : DataImporter {

    override fun importData(data: String?): List<Pair<String, HashMap<String, String>>> {
        val parser = prepareXpp(data)
        val result = mutableListOf<Pair<String, HashMap<String, String>>>()
        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            var tag = ""
            var text: String
            val attrs = hashMapOf<String, String>()
            when (parser.eventType) {
                XmlPullParser.START_TAG -> {
                    tag = parser.name
                    for (i in 0 until parser.attributeCount) {
                        val attributeName = parser.getAttributeName(i)
                        val value = parser.getAttributeValue(i)
                        if (attributeName.isNotBlank() && value.isNotBlank()) {
                            attrs[attributeName] = value
                        }
                    }
                }
                XmlPullParser.TEXT -> {
                    text = parser.text
                    if (text.isNotBlank()) {
                        attrs["text"] = text
                    }
                }
            }
            if (tag.isNotBlank() && attrs.isNotEmpty()) {
                result.add(tag to attrs)
            }
            parser.next()
        }
        return result
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
