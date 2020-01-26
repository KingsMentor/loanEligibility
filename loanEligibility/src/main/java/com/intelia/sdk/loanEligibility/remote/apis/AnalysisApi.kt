package com.intelia.sdk.loanEligibility.remote.apis

import com.intelia.sdk.loanEligibility.remote.NetworkResponses
import com.intelia.sdk.loanEligibility.models.DataRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AnalysisApi {

    @POST("/calculate_eligible_amount")
    fun calculateEligibility(@Body data: DataRequest): Observable<NetworkResponses.DataPointResponse>


}