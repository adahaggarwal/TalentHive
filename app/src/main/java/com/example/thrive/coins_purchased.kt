import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.thrive.Mediation
import com.example.thrive.R
import com.example.thrive.qr

class coins_purchased : Fragment() {

    private lateinit var textView23: TextView
    private lateinit var textView24: TextView
    private lateinit var textView17: TextView
    private lateinit var textView18: TextView
    private lateinit var textView19: TextView
    private lateinit var textView20: TextView
    private lateinit var textView25: TextView
    private lateinit var textView26: TextView
    private lateinit var cl1: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coins_purchased, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize TextViews for coin packs
        textView23 = view.findViewById(R.id.textView23)
        textView24 = view.findViewById(R.id.textView24)
        textView17 = view.findViewById(R.id.textView17)
        textView18 = view.findViewById(R.id.textView18)
        textView19 = view.findViewById(R.id.textView19)
        textView20 = view.findViewById(R.id.textView20)
        textView25 = view.findViewById(R.id.textView25)
        textView26 = view.findViewById(R.id.textView26)
        cl1 = view.findViewById(R.id.cl1)

        cl1.setOnClickListener {
            replaceFragment(qr())
        }

        // Set up the Spinner
        val spinner: Spinner = view.findViewById(R.id.spinCurrencies)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter


        }

        // Set default selection to INR
        spinner.setSelection(0)

        // Handle spinner item selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCurrency = parent.getItemAtPosition(position).toString()
                updateCoinPackValues(selectedCurrency)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
    // Function to update the coin pack values based on selected currency
    private fun updateCoinPackValues(currency: String) {
        when (currency) {
            "INR" -> {
                textView24.text = "Rs. 100"
                textView18.text = "Rs. 150"
                textView20.text = "Rs. 360"
                textView26.text = "Rs. 700"
            }
            "USD" -> {
                textView24.text = "$1.20"
                textView18.text = "$1.80"
                textView20.text = "$4.40"
                textView26.text = "$8.50"
            }
            "EUR" -> {
                textView24.text = "€1.10"
                textView18.text = "€1.65"
                textView20.text = "€4.00"
                textView26.text = "€7.70"
            }
            "GBP" -> {
                textView24.text = "£0.90"
                textView18.text = "£1.35"
                textView20.text = "£3.20"
                textView26.text = "£6.50"
            }
            "AUD" -> {
                textView24.text = "A$1.50"
                textView18.text = "A$2.20"
                textView20.text = "A$5.00"
                textView26.text = "A$9.50"
            }
            "CAD" -> {
                textView24.text = "C$1.55"
                textView18.text = "C$2.30"
                textView20.text = "C$5.10"
                textView26.text = "C$9.80"
            }
            "JPY" -> {
                textView24.text = "¥130"
                textView18.text = "¥195"
                textView20.text = "¥450"
                textView26.text = "¥880"
            }
            "CNY" -> {
                textView24.text = "¥8"
                textView18.text = "¥12"
                textView20.text = "¥30"
                textView26.text = "¥60"
            }
            "RUB" -> {
                textView24.text = "₽90"
                textView18.text = "₽135"
                textView20.text = "₽320"
                textView26.text = "₽650"
            }
            "ZAR" -> {
                textView24.text = "R22"
                textView18.text = "R33"
                textView20.text = "R77"
                textView26.text = "R150"
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = coins_purchased()
    }
}