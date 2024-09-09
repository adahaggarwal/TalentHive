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

        val next2 = findViewById<TextView>(R.id.n1)
        next2.setOnClickListener(){
            val intent = Intent(this, Termsandcon::class.java)
            startActivity(intent)
        }

        val prev2 = findViewById<TextView>(R.id.prev2)
        prev2.setOnClickListener(){
            val intent = Intent(this, Pager2::class.java)
            startActivity(intent)
        }

        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener(){
            val intent = Intent(this, Termsandcon::class.java)
            startActivity(intent)
        }

    }
}