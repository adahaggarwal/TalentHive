package com.example.thrive

import Settingspg
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class NavbarActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up the drawer layout and toggle
        drawerLayout = findViewById(R.id.drawer_layout)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // Set up the navigation view
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.inv-> {
                    replaceFragment(home())
                }
                R.id.dt -> {
                    replaceFragment(Profile())
                }
                R.id.st -> {
                    val intent = Intent(this, Settingspg::class.java)
                    startActivity(intent)
                }
                R.id.lo -> {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
                // Add more cases here for other items if needed
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Set up the bottom navigation view
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
                R.id.chat -> {
                    replaceFragment(Messaging())
                }
            }
            true
        }

        // Load the default fragment if no state is saved
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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
