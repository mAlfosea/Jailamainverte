package fr.perfectblue.jailamainverte.model

data class Plant (
     var plantName: String,
     var plantFamily: String = "",
     var nextArrosage: String = "",
     var arrosageCycle: String = "") {

}