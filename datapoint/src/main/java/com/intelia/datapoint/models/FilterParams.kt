package com.intelia.datapoint.models

object FilterParams {
    val query = mutableListOf<DataPointCategory>(
        DataPointCategory("Firstbank_Cr", mutableListOf("FirstBank"), mutableListOf("Credited"),DataPointType.SMS)
    )
}