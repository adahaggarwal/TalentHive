package com.example.thrive

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

class home : Fragment() {

    private lateinit var profileImageView: ImageView
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register the launcher for image picking
        pickImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                imageUri?.let {
                    // Load the selected image using Glide
                    Glide.with(this)
                        .load(it)
                        .circleCrop()
                        .placeholder(R.drawable.userimgplaceholder)
                        .into(profileImageView)
                }
            }
        }
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

        // Set up the toolbar and navigation drawer
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        drawerLayout = requireActivity().findViewById(R.id.drawer_lay)
        navView = requireActivity().findViewById(R.id.nav_view)

        // Set up the ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            requireActivity(), drawerLayout, toolbar,
            R.string.open, R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set the toolbar as the action bar
        if (requireActivity() is AppCompatActivity) {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        }

        // Set navigation item selection listener
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.inv -> {
                    Toast.makeText(requireContext(), "inv clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.dt -> {
                    Toast.makeText(requireContext(), "dt clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.lo -> {
                    Toast.makeText(requireContext(), "lo clicked", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        // Set OnClickListener for the ImageView to open the gallery
        profileImageView.setOnClickListener {
            if (checkStoragePermission()) {
                openGallery()
            } else {
                requestStoragePermission()
            }
        }

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

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private fun requestStoragePermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PICK_IMAGE_REQUEST && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        }
    }

    private fun checkStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // No need to request permission for SDK < 23
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(navView)) {
//            drawerLayout.closeDrawer(navView)
//        } else {
//            super.onBackPressed()
//        }
//    }
}
