package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Pager2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pager2)

        val next2 = findViewById<TextView>(R.id.next2)
        next2.setOnClickListener(){
            val intent = Intent(this, Pager3::class.java)
            startActivity(intent)




        }
        val prev4 = findViewById<TextView>(R.id.prev4)
        prev4.setOnClickListener() {
            val intent = Intent(this, Pager1::class.java)
            startActivity(intent)
        }

        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener(){
            val intent = Intent(this, Termsandcon::class.java)
            startActivity(intent)
        }

    }
}