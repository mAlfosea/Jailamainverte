package fr.perfectblue.jailamainverte.model

import android.content.Context
import android.content.res.Resources
import fr.perfectblue.jailamainverte.R
import java.util.*

data class CalendarDate (
    var year: String,
    var month: String,
    var day: String,
    var weeklyDay: String,
    var date: Date
) {
}