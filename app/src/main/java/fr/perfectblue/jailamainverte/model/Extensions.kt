package fr.perfectblue.jailamainverte.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fr.perfectblue.jailamainverte.R

fun AppCompatActivity.replaceFragment(frag: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.mainFragment, frag).commit()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}