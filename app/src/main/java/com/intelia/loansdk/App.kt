package com.intelia.loansdk

import android.app.Application
import android.content.Context

open class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}