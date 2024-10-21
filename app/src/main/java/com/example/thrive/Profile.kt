package com.example.thrive

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val CAMERA_REQUEST_CODE = 100

class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var profileImageView: ImageView

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
        profileImageView = view.findViewById(R.id.profile_image)
        val emailEditText = view.findViewById<EditText>(R.id.email)
        val phoneEditText = view.findViewById<EditText>(R.id.phn)
        val languageEditText = view.findViewById<EditText>(R.id.shortbio)
        val countrySpinner = view.findViewById<Spinner>(R.id.spinn)

        // Set country dropdown options (ensure this array exists in your strings.xml)
        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        // Profile image click listener
        profileImageView.setOnClickListener {
            showImagePickerOptions()
        }

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

    private fun showImagePickerOptions() {
        val options = arrayOf("Camera", "Gallery")
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Select Image Source")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera() // Open camera
                1 -> openGallery() // Open gallery
            }
        }
        builder.show()
    }

    private fun openCamera() {
        // Check for permission
        if (ContextCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraImageLauncher.launch(cameraIntent)
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryImageLauncher.launch(galleryIntent)
    }

    private val cameraImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                if (imageBitmap != null) {
                    profileImageView.setImageBitmap(imageBitmap) // Set the image as a bitmap
                } else {
                    Toast.makeText(requireContext(), "Error retrieving image", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private val galleryImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val selectedImageUri: Uri? = result.data?.data
                if (selectedImageUri != null) {
                    profileImageView.setImageURI(selectedImageUri) // Set the image from the gallery
                } else {
                    Toast.makeText(requireContext(), "Error retrieving image", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // Email validation function
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Phone validation function (example for 10 digits)
    private fun isValidPhone(phone: String): Boolean {
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
