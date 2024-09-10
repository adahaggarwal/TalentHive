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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailEditText = view.findViewById<EditText>(R.id.email)
        val phoneEditText = view.findViewById<EditText>(R.id.phno)
        val languageEditText = view.findViewById<EditText>(R.id.lang)
        val countrySpinner = view.findViewById<Spinner>(R.id.spinn)

        // Set country dropdown options (example)
        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        view.findViewById<Button>(R.id.sv).setOnClickListener {
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val language = languageEditText.text.toString()
            val country = countrySpinner.selectedItem.toString()

            var isValid = true


            if (!isValidEmail(email)) {
                emailEditText.error = "Invalid email address"
            }
            if (!isValidPhone(phone)) {
                phoneEditText.error = "Invalid phone number"
            }
            if (country == "Select your country") {
                Toast.makeText(context, "Please select a country", Toast.LENGTH_SHORT).show()
            }
            if (language.isEmpty()) {
                languageEditText.error = "Language is required"
            }
            if (isValid) {
                replaceFragment(home()) // Replace with the current Profile fragment or change to another fragment
            }
            
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



    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}