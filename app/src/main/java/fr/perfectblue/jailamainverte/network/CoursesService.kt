package fr.perfectblue.jailamainverte.network

import fr.perfectblue.jailamainverte.model.Course
import retrofit2.Call
import retrofit2.http.GET

interface CoursesService {
    @GET("/courses")
    fun listCourses() : Call<List<Course>>
}