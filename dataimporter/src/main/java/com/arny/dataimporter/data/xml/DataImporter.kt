package com.arny.dataimporter.data.xml

interface DataImporter {
    fun importData(data: String?): List<Pair<String, HashMap<String, String>>>
}
