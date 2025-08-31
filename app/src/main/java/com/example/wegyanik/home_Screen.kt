package com.example.wegyanik

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.core.net.toUri

class home_Screen : Fragment(R.layout.activity_home) {

    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var projectAdapter: HomeProjectAdapter

//    private val apiService = RetrofitInstance.api
    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)

        val slideModels = arrayListOf(
            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_kit1, ScaleTypes.FIT),
            SlideModel(R.drawable.trishul_new, ScaleTypes.FIT),
            SlideModel(R.drawable.drone_new, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_new_kit, ScaleTypes.FIT),
        )

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        productAdapter = HomeProductAdapter(mutableListOf()) { url ->
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "URL not available", Toast.LENGTH_SHORT).show()
            }
        }

        projectAdapter = HomeProjectAdapter(mutableListOf()) // <-- Initialize here!

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productsRecyclerView.adapter = productAdapter

//        val projectsRecyclerView = view.findViewById<RecyclerView>(R.id.projectsRecyclerView)
//        projectsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        projectsRecyclerView.adapter = projectAdapter

        // Fetch product data
        lifecycleScope.launch {
            val response = RetrofitInstance.api.getProducts()
            if (response.isSuccessful) {
                val products = response.body()?.data ?: emptyList()
                productAdapter.updateData(products)
            } else {
                Log.e("API", "Error response: ${response.code()}")
            }
        }

        // Fetch projects data
        lifecycleScope.launch {
            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    val projectResponse = response.body()
                    val projects = projectResponse ?: emptyList()
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