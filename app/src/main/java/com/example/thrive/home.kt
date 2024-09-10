package com.example.thrive

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views using view.findViewById
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val progressText = view.findViewById<TextView>(R.id.progressText)
        val addsk = view.findViewById<Button>(R.id.addsk)
        val compl = view.findViewById<Button>(R.id.compl)

        // Set progress on the progressBar
        progressBar.progress = 72 // set the progress
        progressText.text = "${progressBar.progress}%"

        // Set listeners for buttons
        addsk.setOnClickListener {
            // Use requireActivity() to start an activity from the fragment
            val intent = Intent(requireActivity(), SelectSkills::class.java)
            startActivity(intent)
        }

        compl.setOnClickListener {
            replaceFragment(Profile())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
