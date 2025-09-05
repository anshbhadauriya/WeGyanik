package com.example.wegyanik

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeProductAdapter(
    private val products: MutableList<Product>,
    private val onProductClick: (String) -> Unit  // Pass URL on click
) : RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {

    class HomeProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val stock: TextView = itemView.findViewById(R.id.productStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_product, parent, false)
        return HomeProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name

        val rupeeFormat = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
        val discounted = rupeeFormat.format(product.discountedPrice)
        val original = rupeeFormat.format(product.originalPrice)

        if (product.originalPrice > product.discountedPrice) {
            val priceText = "$discounted  $original"
            val spannable = SpannableString(priceText)
            val start = discounted.length + 2  // position where original price starts
            spannable.setSpan(
                StrikethroughSpan(),
                start,
                priceText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.price.text = spannable
        } else {
            holder.price.text = discounted
        }

        holder.stock.text = when (product.stock) {
            0 -> "Out of stock"
            else -> "${product.stock} items left"
        }

        val imageUrl = product.gallery.firstOrNull()?.let { "https://wegyanik.in$it" }

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val url = product.detailUrl
            onProductClick(url)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateData(newList: List<Product>) {
        products.clear()
        products.addAll(newList)
        notifyDataSetChanged()
    }
}
