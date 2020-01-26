package xyz.belvi.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intelia.sdk.loanEligibility.models.SmsDataPoint
import com.intelia.loansdk.R
import kotlinx.android.synthetic.main.alert_item.view.title
import kotlinx.android.synthetic.main.category_item.view.*

open class AlertRecyclerAdapter(val smsList: MutableList<SmsDataPoint>) : RecyclerView.Adapter<AlertRecyclerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return smsList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(smsList[position])
    }

    fun update(smsList: MutableList<SmsDataPoint>) {
        this.smsList.clear()
        this.smsList.addAll(smsList)
        notifyDataSetChanged()
    }

    class Holder(iteview: View) : RecyclerView.ViewHolder(iteview) {
        fun bind(sms: SmsDataPoint) {
            itemView.title.text = sms.category
            val adapter = RecyclerAdapter(sms.sms)
            itemView.body_view.layoutManager = (LinearLayoutManager(itemView.context,RecyclerView.VERTICAL,false))
            itemView.body_view.adapter = adapter
        }
    }
}