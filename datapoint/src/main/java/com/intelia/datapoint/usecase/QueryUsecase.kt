package com.intelia.datapoint.usecase

import android.content.Context
import com.intelia.datapoint.models.RelevantApps
import com.intelia.datapoint.models.SmsDataPoint
import io.reactivex.Observable

interface QueryUsecase {
    fun smsData(context: Context): Observable<MutableList<SmsDataPoint>>

    fun relevantApp(): RelevantApps

    fun isRelevantSms(sms: String): Boolean

    fun isRelevantApp(packageName: String): Boolean
}