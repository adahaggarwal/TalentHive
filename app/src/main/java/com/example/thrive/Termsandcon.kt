package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Termsandcon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_termsandcon)

        val ag = findViewById<Button>(R.id.ag)
        ag.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val scrollView  = findViewById<ScrollView>(R.id.sc)
        val scrollToTopButton = findViewById <Button>(R.id.scrollBtn)

        scrollToTopButton.setOnClickListener {
            scrollView.smoothScrollTo(0, 0)
        }

        scrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scrollView.scrollY
            scrollToTopButton.visibility = if (scrollY > 100) {
                Button.VISIBLE
            } else {
                Button.GONE
            }
        }
    }
}