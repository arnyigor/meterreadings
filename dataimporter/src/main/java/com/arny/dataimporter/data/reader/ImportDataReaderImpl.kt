package com.arny.dataimporter.data.reader

import com.arny.metersreading.core.models.base.Params
import com.arny.metersreading.core.models.meter.Meter
import com.arny.metersreading.core.models.meter.MeterAddress
import com.arny.metersreading.core.models.meter.MeterAmount
import com.arny.metersreading.core.models.meter.MeterCompany
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
        const val TAG_METER_ID = "MeterId"
        const val TAG_METER_TYPE_ID = "TypeId"
        const val TAG_METER_NUMBER = "Number"
        const val TAG_METER_COMPANY_ID = "CompanyId"
        const val TAG_ADDRESS_ID = "FlatID"
        const val TAG_AMOUNT = "AMOUNT_ITEM"
        const val TAG_AMOUNT_VALUE = "Amount"
        const val TAG_AMOUNT_DATE = "Date"
        const val TAG_TARIFF_TITLE = "TariffTitle"
        const val TAG_TARIFF_VALUE = "TariffValue"
    }

    override fun readXmlData(data: List<Pair<String, HashMap<String, String>>>): String {
        val sequence = data.asSequence()
        val flats = getFlats(sequence)
        val meters = getMeters(sequence)
        val companies = getCompanies(sequence)
        val amounts = getAmounts(sequence)
        val toString = data.filter { it.first != TAG_AMOUNT }.toString()
        return "flats:\n$flats,\nmeters:\n$meters,\ncompanies:\n$companies,\namounts:\n$amounts,\ndata:\n$toString"
    }

    private fun getMeters(sequenceData: Sequence<Pair<String, HashMap<String, String>>>): List<Meter> {
        return mutableListOf<Meter>().apply {
            for ((_, value) in filteredTag(TAG_METER, sequenceData)) {
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

    private fun getFlats(sequence: Sequence<Pair<String, HashMap<String, String>>>): List<MeterAddress> {
        return mutableListOf<MeterAddress>().apply {
            for ((_, value) in filteredTag(TAG_ADDRESS, sequence)) {
                val id = value[TAG_ID]?.toLongOrNull() ?: 0
                val title = value[TAG_TITLE] ?: ""
                val comment = value[TAG_COMMENTS] ?: ""
                add(MeterAddress(id, title, comment))
            }
        }
    }

    private fun getCompanies(data: Sequence<Pair<String, HashMap<String, String>>>): List<MeterCompany> {
        return mutableListOf<MeterCompany>().apply {
            for ((_, value) in filteredTag(TAG_COMPANY, data)) {
                val id = value[TAG_ID]?.toLongOrNull() ?: 0
                val title = value[TAG_NAME] ?: ""
                add(MeterCompany(id, title))
            }
        }
    }

    private fun getAmounts(data: Sequence<Pair<String, HashMap<String, String>>>): List<MeterAmount> {
        return mutableListOf<MeterAmount>().apply {
            for ((_, value) in filteredTag(TAG_AMOUNT, data)) {
                val id = value[TAG_ID]?.toLongOrNull() ?: 0
                val meterId = value[TAG_METER_ID]?.toLongOrNull() ?: 0
                val amountValue = value[TAG_AMOUNT_VALUE]
                val dateTime = value[TAG_AMOUNT_DATE]
                var tariffKey: String? = null
                var tariffValue: String? = null
                value.entries.filter { it.key == TAG_TARIFF_TITLE || it.key == TAG_TARIFF_VALUE }
                    .map { it.key to it.value }
                    .map { p ->
                        when (p.first) {
                            TAG_TARIFF_TITLE -> {
                                tariffKey = p.second
                            }
                            TAG_TARIFF_VALUE -> {
                                tariffValue = p.second
                            }
                        }
                    }
                val find = find { it.dateTime == dateTime && meterId == meterId }
                var params = find?.data
                if (params == null) {
                    params = Params()
                }
                tariffKey?.let { key ->
                    tariffValue?.let { value ->
                        params.setParam("$key.${TAG_TARIFF_VALUE}", value)
                    }
                }
                amountValue?.let { `val` ->
                    params.setParam("$tariffKey.${TAG_AMOUNT_VALUE}", `val`)
                }
                add(
                    find?.copy(
                        data = params
                    ) ?: MeterAmount(
                        id = id,
                        meterId = meterId,
                        dateTime = dateTime,
                        data = params,
                    )
                )
            }
        }
    }

    private fun filteredTag(
        tag: String,
        sequence: Sequence<Pair<String, HashMap<String, String>>>
    ) =
        sequence.filter { it.first == tag }
}
