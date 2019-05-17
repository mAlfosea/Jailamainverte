package fr.perfectblue.jailamainverte.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.perfectblue.jailamainverte.App
import fr.perfectblue.jailamainverte.MainCoordinatorActivity

import fr.perfectblue.jailamainverte.R
import fr.perfectblue.jailamainverte.adapters.PlantAdapter
import fr.perfectblue.jailamainverte.model.Plant
import kotlinx.android.synthetic.main.fragment_user_plants.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserPlantsFragment : Fragment() {

    var userPlantArray: List<Plant> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_plants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.seedItems()
        this.initialyzeUI()
    }

    private fun initialyzeUI() {
        userPlantsRecycler.layoutManager = LinearLayoutManager(context)
        userPlantsRecycler.adapter = PlantAdapter(App.plants)
    }

    private fun seedItems () {
        doAsync {
            App.plants = App.plantDb.requestPlants()
        }
        this.userPlantArray = MainCoordinatorActivity.USER_PLANTS
    }

}
