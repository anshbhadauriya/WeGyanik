package com.example.wegyanik

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(
    private val onProductClick: (String) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback()) {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: MaterialTextView = itemView.findViewById(R.id.productName)
        val currentPrice: MaterialTextView = itemView.findViewById(R.id.productCurrentPrice)
        val originalPrice: MaterialTextView = itemView.findViewById(R.id.productOriginalPrice)
        val stock: MaterialTextView = itemView.findViewById(R.id.productStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)  // Use ListAdapter method getItem

        holder.name.text = product.name
        val rupeeFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        val discounted = rupeeFormat.format(product.discountedPrice)
        val original = rupeeFormat.format(product.originalPrice)

        holder.currentPrice.text = discounted

        if (product.originalPrice > product.discountedPrice) {
            holder.originalPrice.visibility = View.VISIBLE
            holder.originalPrice.text = original
            holder.originalPrice.paintFlags = holder.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.originalPrice.visibility = View.GONE
            holder.originalPrice.paintFlags = holder.originalPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.stock.text = if (product.stock == 0) "Out of stock" else "${product.stock} items left"

        val imageUrl = product.gallery.firstOrNull()?.let { "https://wegyanik.in$it" }

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val url = product.detailUrl
            if (url.isNotEmpty()) {
                onProductClick(url)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
}
