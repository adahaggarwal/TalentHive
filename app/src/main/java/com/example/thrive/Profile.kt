package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progressText = findViewById<TextView>(R.id.progressText)

        progressBar.progress = 72 // set the progress
        progressText.text = "${progressBar.progress}%"

        val addsk = findViewById<Button>(R.id.addsk)
        addsk.setOnClickListener(){
            val intent = Intent(this, userprofile::class.java)
            startActivity(intent)
        }
    }


}