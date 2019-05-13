package fr.perfectblue.jailamainverte.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fr.perfectblue.jailamainverte.R
import kotlinx.android.synthetic.main.bottom_bar_sheet.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_bar_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem!!.itemId) {
                /*R.id.nav1 -> context!!.toast(getString(R.string.nav1_clicked))
                R.id.nav2 -> context!!.toast(getString(R.string.nav2_clicked))
                R.id.nav3 -> context!!.toast(getString(R.string.nav3_clicked))*/
                R.id.nav1 -> {
                    Toast.makeText(context, "Fav menu item is clicked!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                R.id.nav2 -> {
                    Toast.makeText(context, "Search menu item is clicked!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                R.id.nav3 -> {
                    Toast.makeText(context, "Settings item is clicked!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }
            true
        }
    }
}