package com.intelia.datapoint.models

import java.util.*


enum class DataPointType {
    SMS, APP
}

data class DataPointCategory(
    val category: String,
    val nameFilter: MutableList<String>,
    val contentFilter: MutableList<String>,
    val dataPointType: DataPointType
)

data class Sms(val number: String, val body: String, var date: Date)

data class SmsDataPoint(val category: String, var sms: MutableList<Sms>)


data class RelevantApps(var sms: MutableList<String>)



