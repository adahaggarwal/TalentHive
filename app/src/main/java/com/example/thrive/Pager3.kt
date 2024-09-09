package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pager3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pager3)
        val next3 = findViewById<TextView>(R.id.next3)
        next3.setOnClickListener() {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val prev2 = findViewById<TextView>(R.id.prev2)
        prev2.setOnClickListener() {
            val intent = Intent(this, Pager2::class.java)
            startActivity(intent)
        }

    }
}