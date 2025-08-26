package com.example.wegyanik

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MyOrdersFragment : Fragment(R.layout.fragment_my_orders) {

    private lateinit var recyclerView: RecyclerView
    private val orders = mutableListOf<Order>()
    private val adapter = OrderAdapter(orders)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerOrders)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fetchOrders()
    }

    private fun fetchOrders() {
        lifecycleScope.launch {
//            try {
//                // Replace with your Retrofit call
//                val response = apiService.getOrders()
//                if (response.isSuccessful && response.body() != null) {
//                    orders.clear()
//                    orders.addAll(response.body()!!)
//                    adapter.notifyDataSetChanged()
//                } else {
//                    Toast.makeText(context, "Failed to load orders", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(context, "Network error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}
