package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class OrderAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName = itemView.findViewById<TextView>(R.id.productName)
        private val quantity = itemView.findViewById<TextView>(R.id.quantity)
        private val price = itemView.findViewById<TextView>(R.id.price)
        private val orderDate = itemView.findViewById<TextView>(R.id.orderDate)

        fun bind(order: Order) {
            productName.text = order.productName
            quantity.text = "Qty: ${order.quantity}"
            price.text = "₹${order.price}"
            orderDate.text = order.orderDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size
}
