package com.example.thrive

import android.content.Context
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

object MenuLay {
    fun setupMenu(bottomNavigationView: BottomNavigationView, context: Context) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(context, Profile::class.java)
                    context.startActivity(intent)
                    true
                }
                R.id.user -> {
                    val intent = Intent(context, userprofile::class.java)
                    context.startActivity(intent)
                    true
                }
//                R.id.chat -> {
//                    val intent = Intent(context, ChatActivity::class.java)
//                    context.startActivity(intent)
//                    true
//                }
//                R.id.services -> {
//                    val intent = Intent(context, ServicesActivity::class.java)
//                    context.startActivity(intent)
//                    true
//                }
                R.id.sett -> {
                    val intent = Intent(context, Settingspg::class.java)
                    context.startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
