package com.example.thrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class coins_purchased : Fragment() {

    private lateinit var recyclerViewCoins: RecyclerView
    private lateinit var adapter: CoinsAdapter
    private lateinit var spinnerCurrencies: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coins_purchased, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI components
        spinnerCurrencies = view.findViewById(R.id.spinCurrencies)
        recyclerViewCoins = view.findViewById(R.id.coinsRecyclerView)

        // Set up RecyclerView with LinearLayoutManager and Adapter
        recyclerViewCoins.layoutManager = LinearLayoutManager(context)
        adapter = CoinsAdapter(getCoinPackList("INR")) // Start with default INR coin packs
        recyclerViewCoins.adapter = adapter

        // Set up the Spinner with currencies array
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCurrencies.adapter = adapter
        }

        // Set default selection to INR (index 0)
        spinnerCurrencies.setSelection(0)

        // Handle spinner item selection
        spinnerCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCurrency = parent.getItemAtPosition(position).toString()
                // Update RecyclerView with new currency data
                adapter.updateCoinPackList(getCoinPackList(selectedCurrency))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No action needed
            }
        }
    }

    // Function to return the list of coin packs based on selected currency
    private fun getCoinPackList(currency: String): List<CoinPack> {
        return when (currency) {
            "USD" -> listOf(
                CoinPack("Pack 1", "100 Coins", "$1.20"),
                CoinPack("Pack 2", "150 Coins", "$1.80"),
                CoinPack("Pack 3", "360 Coins", "$4.40"),
                CoinPack("Pack 4", "700 Coins", "$8.50")
            )
            "EUR" -> listOf(
                CoinPack("Pack 1", "100 Coins", "€1.10"),
                CoinPack("Pack 2", "150 Coins", "€1.65"),
                CoinPack("Pack 3", "360 Coins", "€4.00"),
                CoinPack("Pack 4", "700 Coins", "€7.70")
            )
            "GBP" -> listOf(
                CoinPack("Pack 1", "100 Coins", "£0.90"),
                CoinPack("Pack 2", "150 Coins", "£1.35"),
                CoinPack("Pack 3", "360 Coins", "£3.20"),
                CoinPack("Pack 4", "700 Coins", "£6.50")
            )
            // Add more currencies if needed
            else -> listOf(
                CoinPack("Pack 1", "100 Coins", "Rs. 100"),
                CoinPack("Pack 2", "150 Coins", "Rs. 150"),
                CoinPack("Pack 3", "360 Coins", "Rs. 360"),
                CoinPack("Pack 4", "700 Coins", "Rs. 700")
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = coins_purchased()
    }
}
