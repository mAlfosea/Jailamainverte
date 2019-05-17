package fr.perfectblue.jailamainverte.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import fr.perfectblue.jailamainverte.App
import org.jetbrains.anko.db.*

class PlantDbHelper (ctx: Context = App.instance): ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "plant.db"
        val DB_VERSION = 1
        val instance by lazy { PlantDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(PlantTable.TABLENAME, true,
            PlantTable.ID to INTEGER + PRIMARY_KEY,
            PlantTable.PLANTNAME to TEXT,
            PlantTable.PLANTFAMILY to TEXT,
            PlantTable.LASTARROSAGE to TEXT,
            PlantTable.ARROSAGECYCLE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(PlantTable.TABLENAME, true)
        onCreate(db)
    }
}