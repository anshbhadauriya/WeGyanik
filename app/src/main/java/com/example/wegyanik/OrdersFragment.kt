package com.example.wegyanik

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import com.google.android.material.button.MaterialButton
import android.widget.LinearLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyContainer: LinearLayout
    private lateinit var btnShopNow: MaterialButton

    private val orders = mutableListOf<Order>()
    private val adapter = OrderAdapter(orders)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerOrders)
        emptyContainer = view.findViewById(R.id.emptyStateContainer)
        btnShopNow = view.findViewById(R.id.btnShopNow)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        btnShopNow.setOnClickListener {
            openFragment(ProjectFragment())
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).selectedItemId = R.id.nav_products
        }

        fetchOrders()
    }

    private fun fetchOrders() {
        viewLifecycleOwner.lifecycleScope.launch {
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

            if (orders.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyContainer.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyContainer.visibility = View.GONE
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
