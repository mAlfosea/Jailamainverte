package fr.perfectblue.jailamainverte.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fr.perfectblue.jailamainverte.AddActivity

import fr.perfectblue.jailamainverte.R
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.bottom_bar_sheet.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChoosePictureMenuFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_picture_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem!!.itemId) {
                R.id.takePhotoButton -> {
                }
                R.id.chooseFromGalleryButton -> {
                }
            }
            true
        }
    }
}
