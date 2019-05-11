package fr.perfectblue.jailamainverte.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import fr.perfectblue.jailamainverte.R
import kotlinx.android.synthetic.main.fragment_plantlist.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlantlistFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plantlist, container, false)
        var listView: ListView = view.findViewById(R.id.plantListView)
            listView.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.plants))
        
        return view
    }


}
