package com.intelia.sdk.loanEligibility.usecase

import com.intelia.sdk.loanEligibility.models.Eligibility
import com.intelia.sdk.loanEligibility.models.RelevantApps
import com.intelia.sdk.loanEligibility.models.SmsDataPoint
import io.reactivex.Observable

interface QueryUsecase {
    fun smsData(): Observable<MutableList<SmsDataPoint>>

    fun calculateEligibility(): Observable<Eligibility>

    fun relevantApp(): RelevantApps

    fun isRelevantSms(sms: String): Boolean

    fun isRelevantApp(packageName: String): Boolean
}