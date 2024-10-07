package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        // Check if user is already logged in
        if (currentUser != null) {
            // If user is logged in, go directly to home screen (NavbarActivity)
            val intent = Intent(this, NavbarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Close the current activity so the user can't go back to it
        } else {
            // If user is not logged in, show the registration screen
            setContentView(R.layout.activity_register)

            // Initialize views
            val createAccountButton = findViewById<LinearLayout>(R.id.cracc)
            val nameEditText = findViewById<EditText>(R.id.name)
            val ll = findViewById<TextView>(R.id.ll)
            val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val passwordEditText = findViewById<EditText>(R.id.pass)
            val togglePasswordVisibility = findViewById<ImageView>(R.id.togglePasswordVisibility)

            // Navigate to Login screen when "Already have an account?" is clicked
            ll.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

            // Toggle password visibility
            var isPasswordVisible = false
            togglePasswordVisibility.setOnClickListener {
                isPasswordVisible = !isPasswordVisible
                if (isPasswordVisible) {
                    passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    togglePasswordVisibility.setImageResource(R.drawable.eye)
                } else {
                    passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    togglePasswordVisibility.setImageResource(R.drawable.baseline_remove_red_eye_24)
                }
                passwordEditText.setSelection(passwordEditText.text.length)
            }

            // Handle account creation
            createAccountButton.setOnClickListener {
                val name = nameEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (validateInput(name, email, password)) {
                    registerUser(email, password)
                }
            }
        }
    }

    // Validation method for input fields
    private fun validateInput(name: String, email: String, password: String): Boolean {
        if (name.isEmpty() || name.length < 10) {
            Toast.makeText(this, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Register the user with Firebase Authentication
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Account created successfully, navigate to home screen (NavbarActivity)
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NavbarActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // Show error if registration fails
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
