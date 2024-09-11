package com.example.thrive

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class swapp : AppCompatActivity() {

    // Firebase Database reference
    private lateinit var database: DatabaseReference
    private lateinit var userSkillsReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_swapp)

        // Initialize Firebase database reference (adjust path as needed)
        val userId = "your_unique_user_id"  // Replace this with the actual user ID from Firebase Auth
        database = FirebaseDatabase.getInstance().reference
        userSkillsReference = database.child("users").child(userId).child("skills")

        // Find views
        val skillSpinner = findViewById<Spinner>(R.id.skillSpinner)
        val editTextDesiredSkill = findViewById<EditText>(R.id.editTextDesiredSkill)
        val findResultsButton = findViewById<LinearLayout>(R.id.fr)

        // Fetch skills from Firebase and populate the Spinner
        fetchSkillsFromFirebase(skillSpinner)

        // Handle the Find Results button click
        findResultsButton.setOnClickListener {
            val selectedSkill = skillSpinner.selectedItem.toString()
            val desiredSkill = editTextDesiredSkill.text.toString().trim()

            if (desiredSkill.isNotEmpty()) {
                performSkillExchange(selectedSkill, desiredSkill)
            } else {
                Toast.makeText(this, "Please enter a skill you want in exchange", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to fetch skills from Firebase and populate the Spinner
    private fun fetchSkillsFromFirebase(skillSpinner: Spinner) {
        userSkillsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val skills = arrayListOf<String>()
                for (skillSnapshot in dataSnapshot.children) {
                    val skill = skillSnapshot.getValue(String::class.java)
                    if (skill != null) {
                        skills.add(skill)
                    }
                }

                if (skills.isNotEmpty()) {
                    // Create an ArrayAdapter for the Spinner
                    val adapter = ArrayAdapter(this@swapp, android.R.layout.simple_spinner_item, skills)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    skillSpinner.adapter = adapter
                } else {
                    Toast.makeText(this@swapp, "No skills found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@swapp, "Failed to fetch skills: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Function to perform the skill exchange logic
    private fun performSkillExchange(currentSkill: String, desiredSkill: String) {
        // Logic to handle the skill exchange
        Toast.makeText(this, "Exchanging $currentSkill for $desiredSkill", Toast.LENGTH_SHORT).show()

        // You can add logic here to query Firebase for users with the desired skill, etc.
    }
}
