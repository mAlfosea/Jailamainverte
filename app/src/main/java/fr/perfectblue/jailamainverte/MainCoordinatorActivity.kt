package fr.perfectblue.jailamainverte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import fr.perfectblue.jailamainverte.Fragment.BottomNavigationDrawerFragment
import fr.perfectblue.jailamainverte.Fragment.UserPlantsFragment
import fr.perfectblue.jailamainverte.model.Course
import fr.perfectblue.jailamainverte.model.Plant
import fr.perfectblue.jailamainverte.model.replaceFragment
import fr.perfectblue.jailamainverte.network.CoursesService
import kotlinx.android.synthetic.main.activity_main_coordinator.*
import okio.Okio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.nio.charset.Charset

class MainCoordinatorActivity : AppCompatActivity() {

    companion object {
        const val NAME = ""
        const val URL = "https://mobile-courses-server.herokuapp.com"
        val TAG = MainCoordinatorActivity::class.java.simpleName
        var USER_PLANTS: List<Plant> = emptyList()
        lateinit var retrofitInstance: Retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coordinator)
        setSupportActionBar(bottomAppBar)
        replaceFragment(UserPlantsFragment())

        this.init()

        add_plant_floating_button.setOnClickListener { _ ->
            var intent: Intent = Intent(this, AddActivity::class.java)
            //intent.putExtra(NAME, "Géronimo")
            startActivity(intent)
        }
    }

    private fun init() {
        this.createRetroFitInstance()
        this.sendHTTPSRquest()

        var userPlantsTemp = this.getAllPlants()
        if (userPlantsTemp != null) {
            USER_PLANTS = userPlantsTemp
            for (plant in USER_PLANTS) {
                println(plant.plantName)
            }
        }
    }

    private fun sendHTTPSRquest() {
        val service = retrofitInstance.create(CoursesService::class.java)
        val request = service.listCourses()
        request.enqueue (object : Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.i(TAG, "FAIL")
            }

            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                Log.i(TAG, "OK")
                Log.i(TAG, "SIZE: ${response.body()?.size}")
                Log.i(TAG, "${response.body()?.get(3)?.title}")

                val result = response.body()

                if(result != null) {
                    println(result[3].img)
                }
            }

        })
    }

    private fun createRetroFitInstance() {
        retrofitInstance = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
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

    private fun getAllPlants(): List<Plant>? {
        val type = Types.newParameterizedType(List::class.java, Plant::class.java)
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<Plant>> = moshi.adapter(type)

        return jsonAdapter.fromJson(this.loadJsonFromAssets("plants.json"))
    }

    private fun loadJsonFromAssets(filename: String): String {
        try {
            val input = assets.open(filename)
            val source = Okio.buffer(Okio.source(input))
            return source.readByteString().string(Charset.forName("utf-8"))
        } catch (error: IOException) {
            error.printStackTrace()
        }

        return ""
    }
}
