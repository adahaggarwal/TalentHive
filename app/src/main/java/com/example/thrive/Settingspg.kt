import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.thrive.Login
import com.example.thrive.R
import com.google.firebase.auth.FirebaseAuth

class Settingspg : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settingspg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the logout button
        val logoutButton = view.findViewById<Button>(R.id.logout)

        // Set an OnClickListener for the logout button
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    // Function to show a confirmation dialog for logout
    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Do you want to logout?")
            .setPositiveButton("Yes") { dialog, id ->
                // User clicked Yes, so log out
                FirebaseAuth.getInstance().signOut()

                // Redirect to Login Activity
                val intent = Intent(activity, Login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("No") { dialog, id ->
                // User clicked No, so dismiss the dialog
                dialog.dismiss()
            }
        // Create and show the dialog
        builder.create().show()
    }
}
