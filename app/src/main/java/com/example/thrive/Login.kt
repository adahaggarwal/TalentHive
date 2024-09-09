package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val la1 = findViewById<LinearLayout>(R.id.la1)
        la1.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val la2 = findViewById<TextView>(R.id.la2)
        la2.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}