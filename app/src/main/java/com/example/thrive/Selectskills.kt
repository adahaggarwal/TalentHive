package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Selectskills : AppCompatActivity() {

    private lateinit var doneButton: Button
    private lateinit var addSkillButton: Button
    private lateinit var editTextAddSkill: EditText
    private lateinit var skillsAddedLayout: LinearLayout
    private var skillsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectskills)

        doneButton = findViewById(R.id.done)
        addSkillButton = findViewById(R.id.addSkillButton)
        editTextAddSkill = findViewById(R.id.editTextAddSkill)
        skillsAddedLayout = findViewById(R.id.skillsAddedLayout)

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
            val intent = Intent(this, NavbarActivity::class.java)
            startActivity(intent)
        }
    }

    // Dynamically add the skill to the layout
    private fun addSkillToLayout(skill: String) {
        val skillTextView = TextView(this).apply {
            text = skill
            textSize = 16f
            setTextColor(resources.getColor(R.color.black, null))
            setPadding(0, 20, 0, 20)
        }
        skillsAddedLayout.addView(skillTextView)
    }

    // Enable or disable the Done button based on the number of added skills
    private fun updateDoneButtonState() {
        doneButton.isEnabled = skillsList.size >= 3
    }
}
