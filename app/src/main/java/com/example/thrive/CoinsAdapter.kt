package com.example.thrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinsAdapter(private var coinPackList: List<CoinPack>) : RecyclerView.Adapter<CoinsAdapter.CoinPackViewHolder>() {

    // ViewHolder class to hold references to UI components for each list item
    class CoinPackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val packName: TextView = itemView.findViewById(R.id.packName)
        val packCoins: TextView = itemView.findViewById(R.id.packCoins)  // Ensure this ID matches your XML layout
        val packPrice: TextView = itemView.findViewById(R.id.packPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinPackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_pack, parent, false)
        return CoinPackViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinPackViewHolder, position: Int) {
        val coinPack = coinPackList[position]
        holder.packName.text = coinPack.name
        holder.packCoins.text = coinPack.coins  // Correct reference to 'coins'
        holder.packPrice.text = coinPack.price
    }

    override fun getItemCount(): Int {
        return coinPackList.size
    }

    // Function to update the data set when currency changes
    fun updateCoinPackList(newList: List<CoinPack>) {
        coinPackList = newList
        notifyDataSetChanged()
    }
}
