package com.example.wegyanik

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class BannerAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerImage: ImageView = itemView.findViewById(R.id.ivImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_pager, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        Log.d("BannerAdapter", "Binding image at position: $position")
        holder.bannerImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}
