package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Selectskills : AppCompatActivity() {

    private lateinit var doneButton: Button
    private lateinit var skillCheckboxes: List<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.selectskills)

        doneButton = findViewById(R.id.done)

        skillCheckboxes = listOf(
            findViewById(R.id.skillCheckbox1),
            findViewById(R.id.skillCheckbox2),
            findViewById(R.id.skillCheckbox3),
            findViewById(R.id.skillCheckbox4),
            findViewById(R.id.skillCheckbox5),
            findViewById(R.id.skillCheckbox6)
        )

        // Initialize button state
        updateDoneButtonState()

        skillCheckboxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, _ ->
                updateDoneButtonState()
            }
        }

        doneButton.setOnClickListener {
            // Create an Intent to start the NextActivity
            val intent = Intent(this, NavbarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDoneButtonState() {
        val selectedSkillsCount = skillCheckboxes.count { it.isChecked }
        doneButton.isEnabled = selectedSkillsCount >= 3
    }
}
