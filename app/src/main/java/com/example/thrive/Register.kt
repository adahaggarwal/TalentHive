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

        val cracc = findViewById<LinearLayout>(R.id.cracc)
        cracc.setOnClickListener(){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}