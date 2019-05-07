package fr.perfectblue.jailamainverte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    var plants = arrayOf("Aucune", "Orchidee", "Rose")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        activityTitle.text = intent.getStringExtra(MainActivity.NAME)

        this.spinner = plantFamilySpinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, this.plants)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        this.spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Display the selected item text on text view
               // text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }
}
