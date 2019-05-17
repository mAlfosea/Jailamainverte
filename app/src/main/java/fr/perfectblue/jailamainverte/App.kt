package fr.perfectblue.jailamainverte

import android.app.Application
import fr.perfectblue.jailamainverte.model.Plant
import fr.perfectblue.jailamainverte.model.PlantDb
import fr.perfectblue.jailamainverte.model.PlantDbHelper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class App: Application() {
    companion object {
        lateinit var instance: App
        var plants = listOf<Plant>()
        val plantDb by lazy { PlantDb(PlantDbHelper()) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //doAsync { plantDb.savePlant(Plant("Gero", "3", "H", 3)) }

        doAsync {
            plants = plantDb.requestPlants()
            uiThread {
                println("LA BDD: ${plants.size}")
            }
        }

    }


}