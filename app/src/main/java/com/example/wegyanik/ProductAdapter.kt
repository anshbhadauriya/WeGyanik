package com.example.wegyanik

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wegyanik.R
import com.google.android.material.textview.MaterialTextView
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(
    private val productList: MutableList<Product>,
    private val onProductClick: (String) -> Unit  // Lambda to handle clicks, passes URL
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: MaterialTextView = itemView.findViewById(R.id.productName)
        val currentPrice: MaterialTextView = itemView.findViewById(R.id.productCurrentPrice)
        val originalPrice: MaterialTextView = itemView.findViewById(R.id.productOriginalPrice)
        val stock: MaterialTextView = itemView.findViewById(R.id.productStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.name.text = product.name

        // Format prices in Indian Rupees format
        val rupeeFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        val discounted = rupeeFormat.format(product.discountedPrice)
        val original = rupeeFormat.format(product.originalPrice)

        // Set current price text
        holder.currentPrice.text = discounted

        // Set original price with strikethrough if discounted
        if (product.originalPrice > product.discountedPrice) {
            holder.originalPrice.apply {
                visibility = View.VISIBLE
                text = original
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        } else {
            holder.originalPrice.apply {
                visibility = View.GONE
                paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        // Set stock text and color
        holder.stock.text = when (product.stock) {
            0 -> "Out of stock"
            else -> "${product.stock} items left"
        }

        // Load image with Glide
        val imageUrl = product.gallery.firstOrNull()?.let {
            "https://wegyanik.in$it"
        }

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

        // Click listener to open product detail URL
        holder.itemView.setOnClickListener {
            val url = product.detailUrl
            if (url.isNotEmpty()) {
                onProductClick(url)
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}
