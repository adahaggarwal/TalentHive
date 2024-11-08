package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    // Firebase Authentication instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find Views
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.pass)
        val loginButton = findViewById<LinearLayout>(R.id.log)
        val forgotPasswordTextView = findViewById<TextView>(R.id.ftgpass)
        val signUpTextView = findViewById<TextView>(R.id.si)

        // Set up the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(email, password)) {
                loginUser(email, password)
            }
        }

        val jai: TextView = findViewById(R.id.jai)

        jai.setOnClickListener {
            val intent = Intent(this, customwebview::class.java)
            startActivity(intent) // Start the new activity
        }


        // Navigate to Forgot Password screen
        forgotPasswordTextView.setOnClickListener {
            val inftent = Intent(this, ForgotPass::class.java)
            startActivity(intent)
        }

        // Navigate to Register screen
        signUpTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    // Validate the input
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Please enter an email address", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Please enter a password", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Authenticate the user with Firebase
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Snackbar.make(findViewById(android.R.id.content), "Login successful!", Snackbar.LENGTH_SHORT).show()

                    // Navigate to the main screen or dashboard
                    val intent = Intent(this, NavbarActivity::class.java)
                    startActivity(intent)

                } else {

                    Snackbar.make(findViewById(android.R.id.content), "Login failed: ${task.exception?.message}", Snackbar.LENGTH_SHORT).show()
                }

            }
    }
}