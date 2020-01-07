package com.intelia.datapoint.models

object FilterParams {
    val query = mutableListOf<DataPointCategory>(

        DataPointCategory("Airtel_Recharge", mutableListOf("^.*airtelerc.*\$"), mutableListOf("^.*txn.*\$"),DataPointType.SMS),
        DataPointCategory("Sterling_Dr", mutableListOf("^.*sterling.*\$"), mutableListOf("^.*debit.*\$"),DataPointType.SMS),
        DataPointCategory("Sterling_Cr", mutableListOf("^.*sterling.*\$"), mutableListOf("^.*credit.*\$"),DataPointType.SMS),
        DataPointCategory("Firstbank_Cr", mutableListOf("^.*firstbank.*\$"), mutableListOf("^.*credited.*\$"),DataPointType.SMS),
        DataPointCategory("Firstbank_Dr", mutableListOf("^.*firstbank.*\$"), mutableListOf("^.*debited.*\$"),DataPointType.SMS),
        DataPointCategory("Zenith_Dr", mutableListOf("^.*zenithbank.*\$"), mutableListOf("^.*dr amt:.*\$"),DataPointType.SMS),
        DataPointCategory("Zenith_cr", mutableListOf("^.*zeenithbank.*\$"), mutableListOf("^.*cr amt:.*\$"),DataPointType.SMS)
    )
}