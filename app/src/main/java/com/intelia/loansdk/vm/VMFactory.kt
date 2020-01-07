@file:Suppress("UNCHECKED_CAST")

package com.intelia.loansdk.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intelia.datapoint.impl.QueryImpl

object VMFactory : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainVM(QueryImpl()) as T
    }
}