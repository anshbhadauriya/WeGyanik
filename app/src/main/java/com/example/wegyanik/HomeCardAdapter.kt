package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class HomeCard(
    val title: String,
    val subtitle: String,
    val iconResId: Int
)

class HomeCardAdapter(
    private var cards: List<HomeCard>
) : RecyclerView.Adapter<HomeCardAdapter.ViewHolder>() {

    // Optional: for basic filtering
    private var filteredCards = cards.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filteredCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = filteredCards[position]
        holder.title.text = card.title
        holder.subtitle.text = card.subtitle
//        holder.icon.setImageResource(card.iconResId)
    }

    // Simple filter method
    fun filter(query: String) {
        filteredCards = if (query.isEmpty()) {
            cards.toMutableList()
        } else {
            cards.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.subtitle.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val icon: ImageView = itemView.findViewById(R.id.icon)
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
    }
}
