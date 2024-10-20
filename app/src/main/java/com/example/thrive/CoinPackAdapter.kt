package com.example.thrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinPackAdapter(private val coinPacks: List<CoinPack>) :
    RecyclerView.Adapter<CoinPackAdapter.CoinPackViewHolder>() {

    class CoinPackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amountTextView: TextView = view.findViewById(R.id.textViewAmount)
        val priceTextView: TextView = view.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinPackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_pack, parent, false)
        return CoinPackViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinPackViewHolder, position: Int) {
        val coinPack = coinPacks[position]
        holder.amountTextView.text = coinPack.amount
        holder.priceTextView.text = coinPack.price
    }

    override fun getItemCount() = coinPacks.size
}
