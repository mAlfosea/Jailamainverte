package fr.perfectblue.jailamainverte.model

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fr.perfectblue.jailamainverte.R

fun AppCompatActivity.replaceFragment(frag: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.mainFragment, frag).commit()
}