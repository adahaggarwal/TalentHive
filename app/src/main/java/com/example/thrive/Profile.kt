package com.example.thrive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the IDs to match your XML layout
        val emailEditText = view.findViewById<EditText>(R.id.email)
        val phoneEditText = view.findViewById<EditText>(R.id.phn) // Correct ID from XML
        val languageEditText = view.findViewById<EditText>(R.id.shortbio) // Correct ID from XML
        val countrySpinner = view.findViewById<Spinner>(R.id.spinn)

        // Set country dropdown options (ensure this array exists in your strings.xml)
        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        // Save button logic with updated ID
        view.findViewById<Button>(R.id.save).setOnClickListener {
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val language = languageEditText.text.toString()
            val country = countrySpinner.selectedItem.toString()

            var completedFields = 0
            val totalFields = 4

            // Validate and count completed fields
            if (isValidEmail(email)) {
                completedFields++
            } else {
                emailEditText.error = "Invalid email address"
            }

            if (isValidPhone(phone)) {
                completedFields++
            } else {
                phoneEditText.error = "Invalid phone number"
            }

            if (country != "Select your country") {
                completedFields++
            } else {
                Toast.makeText(context, "Please select a country", Toast.LENGTH_SHORT).show()
            }

            if (language.isNotEmpty()) {
                completedFields++
            } else {
                languageEditText.error = "Language is required"
            }

            // Calculate progress
            val progress = (completedFields.toFloat() / totalFields) * 100

            // Pass progress to the home fragment
            val bundle = Bundle()
            bundle.putFloat("progress", progress)
            parentFragmentManager.setFragmentResult("progressUpdate", bundle)

            // Navigate to the landing page
            val landingPageFragment = home() // Create an instance of your LandingPageFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, landingPageFragment) // Replace 'fragment_container' with your container ID
                .addToBackStack(null) // Optionally add to back stack
                .commit()
        }
    }

    // Email validation function
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Phone validation function (example for 10 digits)
    fun isValidPhone(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
