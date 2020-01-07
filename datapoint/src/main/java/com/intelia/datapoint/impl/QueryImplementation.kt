package com.intelia.datapoint.impl

import android.content.Context
import com.intelia.datapoint.models.RelevantApps
import com.intelia.datapoint.models.SmsDataPoint
import com.intelia.datapoint.repository.SmsQuery
import io.reactivex.Observable

internal class QueryImplementation {
    fun smsData(context: Context): Observable<MutableList<SmsDataPoint>> {
        return SmsQuery().smsSearch(context)
    }

    fun relevantApp(): RelevantApps {
        return RelevantApps(mutableListOf())
    }

    fun isRelevantSms(sms: String): Boolean {
        return false
    }

    fun isRelevantApp(packageName: String): Boolean {
        return false
    }
}