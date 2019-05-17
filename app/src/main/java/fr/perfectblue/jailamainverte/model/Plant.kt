package fr.perfectblue.jailamainverte.model

data class Plant (
     var plantName: String,
     var plantFamily: String = "",
     var lastArrosage: String = "",
     var arrosageCycle: String = "") {

}