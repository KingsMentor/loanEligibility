package com.intelia.datapoint.remote

import com.intelia.datapoint.models.Eligibility


sealed class NetworkResponses {

    open class Responses<T>(
        var status: Int = 0,
        val error: Boolean = false,
        val message: String = "",
        val description: String = "",
        val data: T?
    )

    class DataPointResponse : Responses<Eligibility>(data = Eligibility())


}