package com.intelia.datapoint.models

object FilterParams {
    val query = mutableListOf(

        DataPointCategory("Airtel_Recharge", mutableListOf("^.*airtelerc.*\$"), mutableListOf("^.*txn.*\$"),DataPointType.SMS),
        DataPointCategory("Sterling_Dr", mutableListOf("^.*sterling.*\$"), mutableListOf("^.*debit.*\$"),DataPointType.SMS),
        DataPointCategory("Sterling_Cr", mutableListOf("^.*sterling.*\$"), mutableListOf("^.*credit.*\$"),DataPointType.SMS),
        DataPointCategory("Firstbank_Cr", mutableListOf("^.*firstbank.*\$"), mutableListOf("^.*credited.*\$"),DataPointType.SMS),
        DataPointCategory("Firstbank_Dr", mutableListOf("^.*firstbank.*\$"), mutableListOf("^.*debited.*\$"),DataPointType.SMS),
        DataPointCategory("Zenith_Dr", mutableListOf("^.*zenithbank.*\$"), mutableListOf("^.*dr amt:.*\$"),DataPointType.SMS),
        DataPointCategory(
            "Zenith_Cr",
            mutableListOf("^.*zenithbank.*\$"),
            mutableListOf("^.*cr amt:.*\$"),
            DataPointType.SMS
        ),
        DataPointCategory(
            "Diamond_Dr",
            mutableListOf("^.*diamond.*\$"),
            mutableListOf("^.*debit.*\$"),
            DataPointType.SMS
        ),
        DataPointCategory(
            "Diamond_Cr",
            mutableListOf("^.*diamond.*\$"),
            mutableListOf("^.*credit.*\$"),
            DataPointType.SMS
        )
    )
}