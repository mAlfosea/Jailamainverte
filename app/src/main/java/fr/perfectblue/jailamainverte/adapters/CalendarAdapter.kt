package fr.perfectblue.jailamainverte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.perfectblue.jailamainverte.R
import fr.perfectblue.jailamainverte.model.CalendarDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_date_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter (var items: ArrayList<CalendarDate>): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    var callback: Callback? = null
    val clickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            v?.let { callback?.onItemClicked(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_date_calendar, parent, false)

        itemView.setOnClickListener(clickListener)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDate(items[position])
    }


    class ViewHolder (var view: View): RecyclerView.ViewHolder(view), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bindDate (date: CalendarDate) {
            //val formatter = SimpleDateFormat("EEEE, d MMMM yyyy")
            //val format = formatter.format(date)
            itemView.dateText.text = date.day
            itemView.monthText.text = date.month
            itemView.weeklyDay.text = date.weeklyDay

            /*itemView.setOnClickListener {
                itemView.dateText.text = "0"
                println("je clique bien sur la date")
                var recycler: RecyclerView = itemView.parent as RecyclerView
                println("la position de l'item cliqué est ${recycler.getChildLayoutPosition(itemView)}")
                recycler.smoothScrollToPosition(recycler.getChildLayoutPosition(itemView))
            }*/
        }
    }

    interface Callback {
        fun onItemClicked(view: View)
    }
}