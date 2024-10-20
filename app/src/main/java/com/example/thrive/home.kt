package com.example.thrive

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class home : Fragment() {

    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileImageView = view.findViewById(R.id.pimg)

        // Initialize progress bar and buttons
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val progressText = view.findViewById<TextView>(R.id.progressText)
        val addsk = view.findViewById<Button>(R.id.addsk)
        val compl = view.findViewById<Button>(R.id.compl)

        // Set initial progress value
        progressBar.progress = 0
        progressText.text = "${progressBar.progress}%"

        // Set listeners for buttons
        addsk.setOnClickListener {
            val intent = Intent(requireContext(), Selectskills::class.java)
            startActivity(intent)
        }

        compl.setOnClickListener {
            replaceFragment(Profile())
        }

        // Listen for progress updates from Profile fragment
        parentFragmentManager.setFragmentResultListener("progressUpdate", this) { _, result ->
            val progress = result.getFloat("progress", 0f)
            progressBar.progress = progress.toInt()
            progressText.text = "${progressBar.progress}%"
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
