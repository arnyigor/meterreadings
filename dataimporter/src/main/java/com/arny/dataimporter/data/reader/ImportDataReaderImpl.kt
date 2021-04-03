package com.arny.dataimporter.data.reader

import com.arny.dataimporter.data.model.Meter
import com.arny.dataimporter.data.model.MeterAddress
import com.arny.dataimporter.data.model.MeterCompany
import javax.inject.Inject

class ImportDataReaderImpl @Inject constructor() : ImportDataReader {
    private companion object {
        const val TAG_ADDRESS = "FLAT_ITEM"
        const val TAG_COMPANY = "COMPANY"
        const val TAG_ID = "_id"
        const val TAG_TITLE = "Title"
        const val TAG_NAME = "Name"
        const val TAG_COMMENTS = "Comments"
        const val TAG_METER = "METER_ITEM"
        const val TAG_METER_TYPE_ID = "TypeId"
        const val TAG_METER_NUMBER = "Number"
        const val TAG_METER_COMPANY_ID = "CompanyId"
        const val TAG_ADDRESS_ID = "FlatID"
        const val TAG_AMOUNT = "AMOUNT_ITEM"
        const val TAG_TARIFF = "TariffTitle"
    }

    override fun readXmlData(data: List<Pair<String, HashMap<String, String>>>): String {
        val sequence = data.asSequence()
        val flats = getFlats(sequence)
        val meters = getMeters(sequence)
        val companies = getCompanies(sequence)
        val toString = data.filter { it.first != TAG_AMOUNT }.toString()
        return "flats:\n$flats,\nmeters:\n$meters,\ncompanies:\n$companies,\ndata:\n$toString"
    }

    private fun getMeters(sequenceData: Sequence<Pair<String, HashMap<String, String>>>): List<Meter> {
        return mutableListOf<Meter>().apply {
            for ((key, value) in filteredTag(TAG_METER, sequenceData)) {
                if (key == TAG_METER) {
                    val id = value[TAG_ID]?.toLongOrNull()
                    val flatId = value[TAG_ADDRESS_ID]?.toLongOrNull()
                    val typeId = value[TAG_METER_TYPE_ID]?.toLongOrNull()
                    val companyId = value[TAG_METER_COMPANY_ID]?.toLongOrNull()
                    val title = value[TAG_TITLE]
                    val number = value[TAG_METER_NUMBER]
                    add(
                            Meter(
                                    id = id,
                                    title = title,
                                    addressId = flatId,
                                    number = number,
                                    typeId = typeId,
                                    companyId = companyId,
                            )
                    )
                }
            }
        }
    }

    private fun getFlats(sequence: Sequence<Pair<String, HashMap<String, String>>>): List<MeterAddress> {
        return mutableListOf<MeterAddress>().apply {
            for ((key, value) in filteredTag(TAG_ADDRESS, sequence)) {
                if (key == TAG_ADDRESS) {
                    val id = value[TAG_ID]?.toLongOrNull() ?: 0
                    val title = value[TAG_TITLE] ?: ""
                    val comment = value[TAG_COMMENTS] ?: ""
                    add(MeterAddress(id, title, comment))
                }
            }
        }
    }

    private fun getCompanies(data: Sequence<Pair<String, HashMap<String, String>>>): List<MeterCompany> {
        return mutableListOf<MeterCompany>().apply {
            for ((key, value) in filteredTag(TAG_COMPANY, data)) {
                if (key == TAG_COMPANY) {
                    val id = value[TAG_ID]?.toLongOrNull() ?: 0
                    val title = value[TAG_NAME] ?: ""
                    add(MeterCompany(id, title))
                }
            }
        }
    }

    private fun filteredTag(tag: String, sequence: Sequence<Pair<String, HashMap<String, String>>>) =
            sequence.filter { it.first == tag }
}
