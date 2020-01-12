@file:Suppress("UNCHECKED_CAST")

package com.intelia.loansdk.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intelia.datapoint.LoanEligibility
import com.intelia.loansdk.App

object VMFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainVM(LoanEligibility.init(App.appContext)) as T
    }
}