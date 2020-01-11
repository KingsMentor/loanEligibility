package com.intelia.datapoint.impl

import android.content.Context
import com.intelia.datapoint.models.Eligibility
import com.intelia.datapoint.models.HistoryStatus
import com.intelia.datapoint.models.RelevantApps
import com.intelia.datapoint.models.SmsDataPoint
import com.intelia.datapoint.usecase.QueryUsecase
import io.reactivex.Observable

open class QueryImpl : QueryUsecase {
    override fun calculateEligibility(context: Context): Observable<Eligibility> {
        return queryImplementation.calculateEligibility(context)
    }

    private val queryImplementation = QueryImplementation()
    override fun smsData(context: Context): Observable<MutableList<SmsDataPoint>> {
        return queryImplementation.smsData(context)
    }

    override fun relevantApp(): RelevantApps {
        return queryImplementation.relevantApp()
    }

    override fun isRelevantSms(sms: String): Boolean {
        return queryImplementation.isRelevantSms(sms)
    }

    override fun isRelevantApp(packageName: String): Boolean {
        return queryImplementation.isRelevantApp(packageName)
    }
}