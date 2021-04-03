package com.arny.dataimporter.data.reader

interface ImportDataReader {
    fun readXmlData(data: List<Pair<String, HashMap<String, String>>>): String
}