package fr.perfectblue.jailamainverte.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.perfectblue.jailamainverte.R
import fr.perfectblue.jailamainverte.model.Plant
import fr.perfectblue.jailamainverte.model.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_plant_card.view.*

class PlantAdapter(var items:List<Plant>): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_plant_card))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPlant(items[position])
    }

    class ViewHolder (var view: View): RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindPlant(plant: Plant) {
            itemView.plantName.text = plant.plantName
        }
    }
}