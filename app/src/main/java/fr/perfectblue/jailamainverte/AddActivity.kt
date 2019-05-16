package fr.perfectblue.jailamainverte

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.content.FileProvider
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.perfectblue.jailamainverte.Fragment.BottomNavigationDrawerFragment
import fr.perfectblue.jailamainverte.Fragment.ChoosePictureMenuFragment
import fr.perfectblue.jailamainverte.adapters.CalendarAdapter
import fr.perfectblue.jailamainverte.model.CalendarDate
import fr.perfectblue.jailamainverte.model.SliderLayoutManager
import fr.perfectblue.jailamainverte.model.Tools
import kotlinx.android.synthetic.main.activity_add.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList


class AddActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 1
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 0
    }

    lateinit var spinner: Spinner
    lateinit var calendarRecycler: RecyclerView
    var dates = ArrayList<String>(1)
    var localDates = ArrayList<CalendarDate>(1)
    var currentPhotoPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        //activityTitle.text = intent.getStringExtra(MainActivity.NAME)

        this.init()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        changePlantPhotoButton.setOnClickListener {
            val choosePictureMode = ChoosePictureMenuFragment()
            choosePictureMode.show(supportFragmentManager, choosePictureMode.tag)
        }

        createPlantButton.setOnClickListener {
            if (this.validateInputs()) {
                var intent: Intent = Intent(this, MainCoordinatorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun init() {
        this.setPlantsSpinner()
        this.setCalendarRecycler()
    }

    private fun generateDates(): ArrayList<CalendarDate> {
        //this.dates = resources.getStringArray(R.array.dates)
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        for (i in 0 until 30) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            this.localDates.add(CalendarDate(
                calendar.get(Calendar.YEAR).toString(),
                calendar.get(Calendar.MONTH).toString(),
                calendar.get(Calendar.DAY_OF_MONTH).toString(),
                resources.getStringArray(R.array.plants)[calendar.get(Calendar.DAY_OF_WEEK)]))
        }
        println(this.localDates.size)

        this.dates.add("1")
        this.dates.add("14")
        this.dates.add("12")
        this.dates.add("5")
        this.dates.add("7")
        this.dates.add("8")
        this.dates.add("1")
        this.dates.add("21")
        this.dates.add("1")
        this.dates.add("3")
        this.dates.add("8")
        this.dates.add("1")
        return this.localDates
    }

    private fun setPlantsSpinner() {
        this.spinner = plantFamilySpinner

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.plants))
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        this.spinner.adapter = adapter
    }

    private fun setCalendarRecycler() {
        this.calendarRecycler = lastWateringCalendar

        val padding: Int = Tools.getScreenWidth(this)/2 - Tools.dpToPx(this, 40)
        this.calendarRecycler.setPadding(padding, 0, padding, 0)

        this.calendarRecycler.layoutManager = SliderLayoutManager(this).apply {
            callback = object : SliderLayoutManager.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {
                    //tvSelectedItem.setText(dates[layoutPosition])
                    println("LE NUMERO SELECTIONNE: ${localDates[layoutPosition]}")
                }
            }
        }
        //his.calendarRecycler.adapter = CalendarAdapter(this.generateDates())
        this.calendarRecycler.adapter = CalendarAdapter(this.generateDates()).apply {
            callback = object : CalendarAdapter.Callback {
                override fun onItemClicked(view: View) {
                    lastWateringCalendar.smoothScrollToPosition(lastWateringCalendar.getChildLayoutPosition(view))
                }
            }
        }
        lastWateringCalendar.smoothScrollToPosition(this.dates.size - 1)
    }

    private fun validateInputs(): Boolean {
        var result: Boolean = true
        if (planteNameEdit.text.toString().isEmpty()) {
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

    fun selectImageInAlbum() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, REQUEST_SELECT_IMAGE_IN_ALBUM)
    }

    fun takePhoto() {
        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also { // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "fr.perfectblue.jailamainverte",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            galleryAddPic()
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = plantPhotoImg.width
        val targetH: Int = plantPhotoImg.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(currentPhotoPath, this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            plantPhotoImg.setImageBitmap(bitmap)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            this.setPic()
            //val imageBitmap = data?.extras?.get("data") as? Bitmap
            //plantPhotoImg.setImageBitmap(imageBitmap)
        }

        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == Activity.RESULT_OK)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    //val path = saveImage(bitmap)
                    plantPhotoImg!!.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        }
    }
}
