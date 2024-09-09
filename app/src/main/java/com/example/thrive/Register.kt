package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val cacc = findViewById<LinearLayout>(R.id.cacc)
        cacc.setOnClickListener() {
            val intent = Intent(this, Selectskills::class.java)
            startActivity(intent)
        }

        val ca1 = findViewById<TextView>(R.id.ca1)
        cacc.setOnClickListener() {
            val intent = Intent(this, Selectskills::class.java)
            startActivity(intent)
        }

    }
}