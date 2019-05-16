package fr.perfectblue.jailamainverte.model

import android.content.Context
import android.content.res.Resources
import fr.perfectblue.jailamainverte.R

data class CalendarDate (
    var year: String,
    var month: String,
    var day: String,
    var weeklyDay: String
) {

    fun getDay(context: Context, day: Int): String {
        when (day) {
            1 -> return context.getString(R.string.day_1)
            2 -> return context.getString(R.string.day_2)
            3 -> return context.getString(R.string.day_3)
            4 -> return context.getString(R.string.day_4)
            5 -> return context.getString(R.string.day_5)
            6 -> return context.getString(R.string.day_6)
            7 -> return context.getString(R.string.day_7)
            else -> {
               return ""
            }
        }
    }
}