package com.example.wegyanik

import ProductAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.launch

class home_Screen : Fragment(R.layout.activity_home) {

    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var projectAdapter: HomeProjectAdapter

    private val apiService = RetrofitInstance.api
    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)

        val slideModels = arrayListOf(
            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
            SlideModel(R.drawable.robobanner, ScaleTypes.FIT),
            SlideModel(R.drawable.trii, ScaleTypes.FIT),
            SlideModel(R.drawable.drone, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_kit, ScaleTypes.FIT),
        )

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        // Initialize with empty mutable lists
        productAdapter = HomeProductAdapter(mutableListOf())
        projectAdapter = HomeProjectAdapter(mutableListOf())

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productsRecyclerView.adapter = productAdapter

        val projectsRecyclerView = view.findViewById<RecyclerView>(R.id.projectsRecyclerView)
        projectsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        projectsRecyclerView.adapter = projectAdapter

        // Fetch product data
        lifecycleScope.launch {
            try {
                val response = apiService.getProducts()
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    val products = productResponse?.data ?: emptyList()
                    productAdapter.updateData(products)
                } else {
                    Log.e("API", "Error response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception: ${e.localizedMessage}")
            }
        }

        // Fetch projects data
        lifecycleScope.launch {
            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    val projects = response.body() ?: emptyList()
                    projectAdapter.updateData(projects)
                } else {
                    Log.e("API", "Error response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception: ${e.localizedMessage}")
            }
        }
    }
}
