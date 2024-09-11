package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Selectskills : AppCompatActivity() {

    private lateinit var doneButton: Button
    private lateinit var addSkillButton: Button
    private lateinit var editTextAddSkill: EditText
    private lateinit var skillsAddedLayout: LinearLayout
    private var skillsList = mutableListOf<String>()

    // Firebase Database reference
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectskills)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().reference.child("users").child("skills")

        doneButton = findViewById(R.id.done)
        addSkillButton = findViewById(R.id.addSkillButton)
        editTextAddSkill = findViewById(R.id.editTextAddSkill)
        skillsAddedLayout = findViewById(R.id.skillsAddedLayout)

        fetchSkillsFromFirebase()

        // Handle adding new skill
        addSkillButton.setOnClickListener {
            val newSkill = editTextAddSkill.text.toString().trim()
            if (newSkill.isNotEmpty()) {
                if (skillsList.contains(newSkill)) {
                    Toast.makeText(this, "Skill already added", Toast.LENGTH_SHORT).show()
                } else {
                    addSkillToLayout(newSkill)
                    skillsList.add(newSkill)
                    editTextAddSkill.text.clear()
                    updateDoneButtonState()
                }
            } else {
                Toast.makeText(this, "Please enter a skill", Toast.LENGTH_SHORT).show()
            }
        }



        // Set done button click listener
        doneButton.setOnClickListener {
            if (skillsList.size >= 3) {
                saveSkillsToFirebase()
                val intent = Intent(this, NavbarActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please add at least 3 skills", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchSkillsFromFirebase() {
        val userId = "your_unique_user_id" // Ideally, replace with a real user ID (from Firebase Auth)
        val userSkillsReference = database.child(userId)

        userSkillsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                skillsList.clear()
                skillsAddedLayout.removeAllViews()

                for (skillSnapshot in snapshot.children) {
                    val skill = skillSnapshot.getValue(String::class.java)
                    if (skill != null) {
                        addSkillToLayout(skill)
                        skillsList.add(skill)
                    }
                }
                updateDoneButtonState()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Selectskills, "Failed to load skills", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Dynamically add the skill to the layout
    private fun addSkillToLayout(skill: String) {
        val inflater = layoutInflater
        val skillView = inflater.inflate(R.layout.skill_item, skillsAddedLayout, false)

        val skillTextView = skillView.findViewById<TextView>(R.id.skillText)
        skillTextView.text = skill

        val deleteButton = skillView.findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener {
            skillsAddedLayout.removeView(skillView)
            skillsList.remove(skill)
            updateDoneButtonState()
        }

        skillsAddedLayout.addView(skillView)
    }

    // Save skills to Firebase
    private fun saveSkillsToFirebase() {
        val userId = "your_unique_user_id" // Ideally, replace with a real user ID (from Firebase Auth)
        val userSkillsReference = database.child(userId)

        userSkillsReference.setValue(skillsList)
            .addOnSuccessListener {
                Toast.makeText(this, "Skills saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save skills", Toast.LENGTH_SHORT).show()
            }
    }

    // Enable or disable the Done button based on the number of added skills
    private fun updateDoneButtonState() {
        doneButton.isEnabled = skillsList.size >= 3
    }
}
