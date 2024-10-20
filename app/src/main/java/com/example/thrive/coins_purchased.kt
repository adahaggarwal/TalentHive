package com.example.thrive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class coins_purchased : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var coinPackAdapter: CoinPackAdapter
    private val coinPacks = mutableListOf<CoinPack>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coins_purchased, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewCoinPacks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load initial data
        loadCoinPackData("INR")

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

        // Handle spinner item selection
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCurrency = parent.getItemAtPosition(position).toString()
                loadCoinPackData(selectedCurrency)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    // Function to load coin pack data based on selected currency
    private fun loadCoinPackData(currency: String) {
        coinPacks.clear()
        when (currency) {
            "INR" -> {
                coinPacks.add(CoinPack("100 Coins", "Rs. 100"))
                coinPacks.add(CoinPack("150 Coins", "Rs. 150"))
                coinPacks.add(CoinPack("360 Coins", "Rs. 360"))
                coinPacks.add(CoinPack("700 Coins", "Rs. 700"))
            }
            "USD" -> {
                coinPacks.add(CoinPack("100 Coins", "$1.20"))
                coinPacks.add(CoinPack("150 Coins", "$1.80"))
                coinPacks.add(CoinPack("360 Coins", "$4.40"))
                coinPacks.add(CoinPack("700 Coins", "$8.50"))
            }
            // Add cases for other currencies...
        }
        coinPackAdapter = CoinPackAdapter(coinPacks)
        recyclerView.adapter = coinPackAdapter
    }
}
