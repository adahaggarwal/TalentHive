package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Pager1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pager2)

        var next1 = findViewById<TextView>(R.id.next1)
        next1.setOnClickListener(){
            val intent = Intent(this, Pager2::class.java)

        }

    }
}