package com.example.thrive

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class SelectSkills : AppCompatActivity() {

    private lateinit var skillContainers: List<LinearLayout>
    private lateinit var progressBar: ProgressBar
    private lateinit var doneButton: Button
    private var selectedSkillsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.selectskills)

        // Initialize views
        progressBar = findViewById(R.id.topLine)
        doneButton = findViewById(R.id.done)

        // List of skill containers (for all 6 skills)
        skillContainers = listOf(
            findViewById(R.id.skillUI),
            findViewById(R.id.skillUX),
            findViewById(R.id.skillResearch),
            findViewById(R.id.skillWriting),
            findViewById(R.id.skillTesting),
            findViewById(R.id.skillServiceDesign)
        )

        // Set click listeners for skill selection
        skillContainers.forEach { skillLayout ->
            skillLayout.setOnClickListener {
                toggleSkillSelection(skillLayout)
            }
        }

        // Initially hide the progress bar (if less than 3 skills selected)
        updateProgressBar()

        // Set done button functionality
        doneButton.setOnClickListener {
            if (selectedSkillsCount >= 3) {

            }
        }
    }

    // Toggle selection of a skill (add/remove skill selection)
    private fun toggleSkillSelection(skillLayout: LinearLayout) {
        val isSelected = skillLayout.isSelected

        if (isSelected) {
            selectedSkillsCount--
            skillLayout.isSelected = false
            skillLayout.setBackgroundResource(R.drawable.cr4bffffff) // default background
        } else {
            selectedSkillsCount++
            skillLayout.isSelected = true
            skillLayout.setBackgroundResource(R.drawable.cr4bffffff) // selected background
        }

        // Update progress bar based on the selected skills count
        updateProgressBar()
    }

    // Update the progress bar based on skill selection
    private fun updateProgressBar() {
        if (selectedSkillsCount >= 3) {
            progressBar.isVisible = true
            progressBar.progress = 100 // Full progress when 3 or more skills are selected
        } else {
            progressBar.isVisible = false
            progressBar.progress = 0 // No progress if less than 3 skills are selected
        }
    }
}
