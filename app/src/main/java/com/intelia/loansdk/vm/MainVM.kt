package com.intelia.loansdk.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intelia.datapoint.models.Eligibility
import com.intelia.datapoint.models.SmsDataPoint
import com.intelia.datapoint.usecase.QueryUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


open class MainVM(private val usecase: QueryUsecase) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    val smsDataPoint = MutableLiveData<MutableList<SmsDataPoint>>()
    val eligibility = MutableLiveData<Eligibility>()
    fun querySms() {
        usecase.smsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                smsDataPoint.postValue(it)
            }
    }

    fun calculateEligibility() {

        usecase.calculateEligibility()
            .doOnError {
                eligibility.postValue(null)
            }
            .onErrorResumeNext(io.reactivex.Observable.empty())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                eligibility.postValue(it)
            }
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}