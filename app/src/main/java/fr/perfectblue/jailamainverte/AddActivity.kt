package fr.perfectblue.jailamainverte

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_add.*


class AddActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

    lateinit var spinner: Spinner
    var plants = arrayOf("Aucune", "Orchidee", "Rose")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //activityTitle.text = intent.getStringExtra(MainActivity.NAME)

        this.spinner = plantFamilySpinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.plants))
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        this.spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        plantPhotoButton.setOnClickListener {
            this.takePhoto()
        }

        plantGalleryButton.setOnClickListener {
            this.selectImageInAlbum()
        }

        createPlantButton.setOnClickListener {
            if (this.validateInputs()) {
                var intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(): Boolean {
        var result: Boolean = true
        if(planteNameEdit.text.toString().isEmpty()) {
            planteNameEdit.error = getString(R.string.plant_name_edit_error)
            result = false
        }

        if (plantFamilySpinner.selectedItem.toString() == resources.getStringArray(R.array.plants)[0]) {
            (plantFamilySpinner.selectedView as TextView).error = getString(R.string.plant_family_edit_error)
            result = false
        }

        if (wateringCycleEditText.text.toString().isEmpty()) {
            wateringCycleEditText.error = getString(R.string.plant_cycle_edit_error)
            result = false
        }
        return result
    }

    private fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }
    private fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_TAKE_PHOTO ->{
                if(resultCode== Activity.RESULT_OK && data !=null){
                    plantPhotoImg.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(this,"Unrecognized request code",Toast.LENGTH_SHORT)
            }
        }
    }
}
