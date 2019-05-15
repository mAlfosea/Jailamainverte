package fr.perfectblue.jailamainverte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.RecyclerView
import fr.perfectblue.jailamainverte.R
import fr.perfectblue.jailamainverte.model.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.item_date_calendar.view.*
import javax.security.auth.callback.Callback

class CalendarAdapter (var items: ArrayList<String>): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_date_calendar, parent, false)

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

        fun bindDate (date: String) {
            itemView.dateText.text = date

            itemView.setOnClickListener {
                itemView.dateText.text = "0"
                println("je clique bien sur la date")
                //var recycler: RecyclerView = itemView.findViewById(R.id.lastWateringCalendar)
                //println("la position de l'item cliqué est ${recycler.getChildLayoutPosition(itemView)}")
                //lastWateringCalendar.smoothScrollToPosition(lastWateringCalendar.getChildLayoutPosition(itemView))
            }
        }
    }


}