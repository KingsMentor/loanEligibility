package com.intelia.sdk.loanEligibility.impl

import android.content.Context
import com.intelia.sdk.loanEligibility.models.Eligibility
import com.intelia.sdk.loanEligibility.models.RelevantApps
import com.intelia.sdk.loanEligibility.models.SmsDataPoint
import com.intelia.sdk.loanEligibility.usecase.QueryUsecase
import io.reactivex.Observable

open class QueryImpl : QueryUsecase {

    private constructor()

    private lateinit var context: Context

    internal constructor(context: Context) : this() {
        this.context = context
    }

    private val queryImplementation = QueryImplementation()

    override fun calculateEligibility(): Observable<Eligibility> {
        return queryImplementation.calculateEligibility(context)
    }

    override fun smsData(): Observable<MutableList<SmsDataPoint>> {
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