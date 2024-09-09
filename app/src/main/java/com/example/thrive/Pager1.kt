package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.thrive.R.*

class Pager1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.pager1)


        val next1 = findViewById<TextView>(R.id.nex)
        next1.setOnClickListener(){
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