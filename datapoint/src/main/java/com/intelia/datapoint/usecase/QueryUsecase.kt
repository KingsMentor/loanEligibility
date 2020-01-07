package com.intelia.datapoint.usecase

import com.intelia.datapoint.models.RelevantApps
import com.intelia.datapoint.models.SmsDataPoint

interface QueryUsecase {
    fun smsData(): SmsDataPoint

    fun relevantApp(): RelevantApps

    fun isRelevantSms(sms: String): Boolean

    fun isRelevantApp(packageName: String): Boolean
}