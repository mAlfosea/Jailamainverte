package fr.perfectblue.jailamainverte.model

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class PlantDb (private val dbHelper: PlantDbHelper = PlantDbHelper.instance) {
    fun requestPlants () = dbHelper.use {
        select(PlantTable.TABLENAME,
            PlantTable.ID,
            PlantTable.PLANTNAME,
            PlantTable.PLANTFAMILY,
            PlantTable.LASTARROSAGE,
            PlantTable.ARROSAGECYCLE).parseList(classParser<Plant>())
    }

    fun savePlant (plant: Plant) = dbHelper.use {
        insert(PlantTable.TABLENAME,
            PlantTable.PLANTNAME to plant.plantName,
            PlantTable.PLANTFAMILY to plant.plantFamily,
            PlantTable.LASTARROSAGE to plant.lastArrosage,
            PlantTable.ARROSAGECYCLE to plant.arrosageCycle)
    }

    fun savePlants(plantList: List<Plant>) {
        for (c in plantList)
            savePlant(c)
    }
}

