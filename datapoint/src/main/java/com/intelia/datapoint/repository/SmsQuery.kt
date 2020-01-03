package com.intelia.datapoint.repository

import android.content.Context
import android.provider.Telephony
import com.intelia.datapoint.models.Sms
import io.reactivex.Observable
import java.util.*

open class SmsQuery {

    fun inboxSMS(context: Context): Observable<MutableList<Sms>> {
        return Observable.create<MutableList<Sms>> { emitter ->
            val cr = context.contentResolver
            val c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
            if (c != null) {
                val smsList = mutableListOf<Sms>()
                while (c.moveToNext()) {
                    val smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE))
                    val number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val dateFormat = Date(smsDate.toLong())
                    val type =
                        Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))
                    if (type == Telephony.Sms.MESSAGE_TYPE_INBOX)
                        smsList.add(Sms(number, body, dateFormat))
                }
                c.close()
                emitter.onNext(smsList)
            }
            emitter.onComplete()
        }
    }
}