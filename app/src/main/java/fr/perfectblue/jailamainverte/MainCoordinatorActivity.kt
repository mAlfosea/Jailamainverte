package fr.perfectblue.jailamainverte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import fr.perfectblue.jailamainverte.Fragment.BottomNavigationDrawerFragment
import fr.perfectblue.jailamainverte.Fragment.UserPlantsFragment
import fr.perfectblue.jailamainverte.model.replaceFragment
import kotlinx.android.synthetic.main.activity_main_coordinator.*

class MainCoordinatorActivity : AppCompatActivity() {

    companion object {
        const val NAME = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coordinator)
        setSupportActionBar(bottomAppBar)
        replaceFragment(UserPlantsFragment())

        add_plant_floating_button.setOnClickListener { _ ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            var intent: Intent = Intent(this, AddActivity::class.java)
            //intent.putExtra(NAME, "Géronimo")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.botton_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.app_bar_fav -> Toast.makeText(this, "Fav menu item is clicked!", Toast.LENGTH_SHORT).show()
            R.id.app_bar_search -> Toast.makeText(this, "Search menu item is clicked!", Toast.LENGTH_SHORT).show()
            R.id.app_bar_settings -> Toast.makeText(this, "Settings item is clicked!", Toast.LENGTH_SHORT).show()
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }

        return true
    }

}
