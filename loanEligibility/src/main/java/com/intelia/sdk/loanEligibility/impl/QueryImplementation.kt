package com.intelia.sdk.loanEligibility.impl

import android.content.Context
import com.intelia.loanEligibility.models.*
import com.intelia.sdk.loanEligibility.models.*
import com.intelia.sdk.loanEligibility.remote.ApiClient
import com.intelia.sdk.loanEligibility.remote.apis.AnalysisApi
import com.intelia.sdk.loanEligibility.repository.SmsQuery
import io.reactivex.Observable
import java.util.*
import java.util.regex.Pattern

internal class QueryImplementation(private val api: AnalysisApi = ApiClient.retrofit.create(
    AnalysisApi::class.java)) {

    fun calculateEligibility(context: Context): Observable<Eligibility> {
        return SmsQuery().smsSearch(context)
            .map {
                val body = mutableListOf<Request>()
                it.forEach { sdp ->
                    body.addAll(sdp.sms.map {
                        Request(
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
                it.addAll(relevantApp().apps.map {
                    Request(
                        "",
                        it,
                        "",
                        Date(),
                        false
                    )
                })
                it
            }.flatMap { api.calculateEligibility(
                DataRequest(
                    it
                )
            ) }
            .map {
                it
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