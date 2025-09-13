package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wegyanik.ProductAdapter
import com.example.wegyanik.ProductRepository
import com.example.wegyanik.R
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var shimmerLayout: ShimmerFrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.productRecyclerView)
        shimmerLayout = view.findViewById(R.id.shimmerLayoutProducts)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        productAdapter = ProductAdapter { url ->
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
                startActivity(intent)
            }
        }
        recyclerView.adapter = productAdapter

        fetchProductData()
    }

    // Show full-screen shimmer
    private fun showShimmer() {
        shimmerLayout.visibility = View.VISIBLE
        shimmerLayout.startShimmer()
        recyclerView.visibility = View.GONE
    }

    // Hide shimmer and show RecyclerView
    private fun hideShimmer() {
        shimmerLayout.stopShimmer()
        shimmerLayout.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun fetchProductData() {
        viewLifecycleOwner.lifecycleScope.launch {
            showShimmer() // start shimmer while loading

            try {
                // Load from cache first
                val cached = ProductRepository.getCachedProducts()
                if (!cached.isNullOrEmpty()) {
                    productAdapter.submitList(cached)
                    hideShimmer()
                    Log.d("ProductFragment", "Loaded products from cache")
                    return@launch
                }

                // Fetch from API if cache empty
                val response = RetrofitInstance.api.getProducts()
                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!.data

                    // Save in cache
                    ProductRepository.saveProducts(products)

                    // Update adapter
                    productAdapter.submitList(products)
                    Log.d("ProductFragment", "Fetched products from API")
                } else {
                    Log.e("ProductFragment", "API call failed with code ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProductFragment", "Exception: ${e.message}")
            } finally {
                hideShimmer() // stop shimmer
            }
        }
    }
}
