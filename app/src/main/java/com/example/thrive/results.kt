package com.example.thrive

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class results : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Get the list of users from the intent
        val usersWithDesiredSkill = intent.getStringArrayListExtra("usersWithDesiredSkill")

        if (usersWithDesiredSkill != null && usersWithDesiredSkill.isNotEmpty()) {
            // Display the users in a ListView or other UI element
            val listView = findViewById<ListView>(R.id.resultsListView)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usersWithDesiredSkill)
            listView.adapter = adapter
        } else {
//            Toast.makeText(this, "No users found offering the desired skill", Toast.LENGTH_SHORT).show()
        }
    }
}
