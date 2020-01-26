package com.intelia.sdk.loanEligibility.remote

import com.intelia.sdk.loanEligibility.models.Eligibility


sealed class NetworkResponses {



    class DataPointResponse : Eligibility()


}