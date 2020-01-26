package xyz.belvi.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intelia.sdk.loanEligibility.models.Sms
import com.intelia.loansdk.R
import kotlinx.android.synthetic.main.alert_item.view.*

open class RecyclerAdapter(val smsList: MutableList<Sms>) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.alert_item, parent, false))
    }

    override fun getItemCount(): Int {
        return smsList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(smsList[position])
    }

//    fun update(smsList: MutableList<Sms>) {
//        this.smsList.clear()
//        this.smsList.addAll(smsList)
//        notifyDataSetChanged()
//    }

    class Holder(iteview: View) : RecyclerView.ViewHolder(iteview) {
        fun bind(sms: Sms) {
            itemView.title.text = sms.number
            itemView.body.text = sms.body
        }
    }
}