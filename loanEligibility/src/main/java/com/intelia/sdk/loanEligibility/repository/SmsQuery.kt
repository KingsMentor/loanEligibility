package com.intelia.sdk.loanEligibility.repository

import android.content.Context
import android.provider.Telephony
import com.intelia.sdk.loanEligibility.models.FilterParams
import com.intelia.sdk.loanEligibility.models.Sms
import com.intelia.sdk.loanEligibility.models.SmsDataPoint
import io.reactivex.Observable
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.LinkedHashMap

open class SmsQuery {

    fun smsSearch(context: Context): Observable<MutableList<SmsDataPoint>> {
        return Observable.create<MutableList<SmsDataPoint>> { emitter ->
            val cr = context.contentResolver

            val c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
            if (c != null) {
                val smsList = LinkedHashMap<String,MutableList<Sms>>()
                while (c.moveToNext()) {
                    val smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE))
                    val number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val dateFormat = Date(smsDate.toLong())
                    val type =
                        Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))
                    if (type == Telephony.Sms.MESSAGE_TYPE_INBOX) {
                        FilterParams.query.forEach outter@{ dataPointCategory ->
                            var numberMatch = false
                            var contentMatch = false
                            dataPointCategory.nameFilter.forEach inner@{
                                if (numberMatch) {
                                    return@inner
                                }
                                val p = Pattern.compile(it)
                                val m = p.matcher(number.toLowerCase())
                                numberMatch = m.matches()
                            }
                            dataPointCategory.contentFilter.forEach inner@{
                                if (contentMatch) {
                                    return@inner
                                }
                                val p = Pattern.compile(it)
                                val m = p.matcher(body.replace("\n"," ").toLowerCase())
                                contentMatch = m.matches()
                            }
                            if (numberMatch && contentMatch) {
                                if(smsList.containsKey(dataPointCategory.category))
                                    smsList[dataPointCategory.category]?.add(
                                        Sms(
                                            number,
                                            body,
                                            dateFormat
                                        )
                                    )
                                else {
                                    smsList[dataPointCategory.category] = mutableListOf(
                                        Sms(
                                            number,
                                            body,
                                            dateFormat
                                        )
                                    )
                                }
                                return@outter
                            }
                        }
                    }

                }
                c.close()
                emitter.onNext(smsList.map {
                    SmsDataPoint(
                        it.key,
                        it.value
                    )
                }.toMutableList())
            }
            emitter.onComplete()
        }
    }
}