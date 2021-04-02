package com.arny.dataimporter.data.xml

import com.arny.dataimporter.data.model.Address
import com.arny.dataimporter.data.model.Meter
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.inject.Inject

class XmlImporterImpl @Inject constructor() : DataImporter {
    private companion object {
        const val TAG_ADDRESS = "FLAT_ITEM"
        const val TAG_ID = "_id"
        const val TAG_TITLE = "Title"
        const val TAG_COMMENTS = "Comments"
        const val TAG_METER = "METER_ITEM"
        const val TAG_METER_ID = "MeterId"
        const val TAG_ADDRESS_ID = "FlatID"
        const val TAG_AMOUNT = "AMOUNT_ITEM"
    }

    override fun importData(data: String?): String {
        val parser = prepareXpp(data)
        val toString = StringBuilder().apply {
            while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                var tag = ""
                var text: String
                var address: Address? = null
                val meters = mutableListOf<Meter>()
                var meter: Meter
                when (parser.eventType) {
                    XmlPullParser.START_TAG -> {
                        tag = parser.name
                        append("tag:$tag")
                        append("\n")
                        var id = 0L
                        var title = ""
                        var comments = ""
                        for (i in 0 until parser.attributeCount) {
                            val attributeName = parser.getAttributeName(i)
                            val value = parser.getAttributeValue(i)
                            append("attr:$attributeName->val:$value\n")
                            when (tag) {
                                TAG_ADDRESS -> {
                                    when (attributeName) {
                                        TAG_ID -> {
                                            id = value.toLong()
                                        }
                                        TAG_TITLE -> {
                                            title = value
                                        }
                                        TAG_COMMENTS -> {
                                            comments = value
                                        }
                                    }
                                }
                                TAG_METER -> {
                                    when (attributeName) {
                                        TAG_ID -> {
                                            id = value.toLong()
                                        }
                                        TAG_TITLE -> {
                                            title = value
                                        }
                                        TAG_COMMENTS -> {
                                            comments = value
                                        }
                                    }
                                }
                            }
                        }
                        address = Address(id, title, comments)
                        append("\n")
                    }
                    XmlPullParser.TEXT -> {
                        text = parser.text
                        append("text:$text")
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
