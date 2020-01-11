package com.intelia.datapoint.impl

import android.content.Context
import com.intelia.datapoint.models.*
import com.intelia.datapoint.remote.ApiClient
import com.intelia.datapoint.remote.apis.AnalysisApi
import com.intelia.datapoint.repository.SmsQuery
import io.reactivex.Observable
import java.util.*
import java.util.regex.Pattern

internal class QueryImplementation(private val api: AnalysisApi = ApiClient.retrofit.create(AnalysisApi::class.java)) {
    fun calculateEligibility(context: Context): Observable<Eligibility> {
        return SmsQuery().smsSearch(context)
            .map {
                val body = mutableListOf<DataRequest>()
                it.forEach { sdp ->
                    body.addAll(sdp.sms.map {
                        DataRequest(
                            sdp.category,
                            it.number,
                            it.body,
                            it.date,
                            true
                        )
                    })
                }
                body
            }.map {
                it.addAll(relevantApp().apps.map { DataRequest("", it, "", Date(), false) })
                it
            }.flatMap { api.calculateEligibility(it) }
            .map {
                it.data
            }
    }



    fun smsData(context: Context): Observable<MutableList<SmsDataPoint>> {
        return SmsQuery().smsSearch(context)
    }

    fun relevantApp(): RelevantApps {
        return RelevantApps(mutableListOf())
    }

    fun isRelevantSms(sms: String): Boolean {
        var matches = false
        FilterParams.query.forEach {
            if (matches)
                return matches
            it.contentFilter.forEach {
                if (matches)
                    return matches
                val p = Pattern.compile(it)
                val m = p.matcher(sms.toLowerCase())
                matches = m.matches()

            }
        }
        return matches
    }

    fun isRelevantApp(packageName: String): Boolean {
        return false
    }
}