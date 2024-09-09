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

        val ftgpass = findViewById<TextView>(R.id.ftgpass)
        ftgpass.setOnClickListener(){
            val intent = Intent(this, ForgotPass::class.java)
            startActivity(intent)
        }

        val log = findViewById<LinearLayout>(R.id.log)
        log.setOnClickListener(){
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val si = findViewById<TextView>(R.id.si)
        si.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}