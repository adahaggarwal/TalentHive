package com.example.thrive

import android.os.Bundle
import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        val bottomNav = findViewById<BottomNavigationView>(R.id.btmnav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ho -> {
                    replaceFragment(home())
                }

                R.id.sett -> {
                    replaceFragment(Settingspg())
                }


                R.id.prof -> {
                    replaceFragment(Profile())
                }
                R.id.serv -> {
                    replaceFragment(Services())
                }
                // R.id.chat -> replaceFragment(ChatFragment())
            }
            true
        }
        if (savedInstanceState == null) {
            replaceFragment(home())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
