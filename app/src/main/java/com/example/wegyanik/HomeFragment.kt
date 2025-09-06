package com.example.wegyanik

import ProjectApiService
import RetrofitInstance
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.activity_home) {

    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var projectAdapter: HomeProjectAdapter
    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)

        val slideModels = arrayListOf(
            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
            SlideModel(R.drawable.trishul_new, ScaleTypes.FIT),
            SlideModel(R.drawable.drone_new, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_new_kit, ScaleTypes.FIT)
        )

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                when (position) {
                    0 -> showProductInfo(
                        "Vaman",
                        "Meet our Robotic Dog — a smart, agile and interactive four-legged companion built to mimic real canine behavior. Designed for education, exploration, and entertainment, this robotic pet can walk, sit, respond to voice commands, and express emotions via LED eyes. Powered by precise servo motors and sensors, it navigates and avoids obstacles. Perfect for students and hobbyists, supports Arduino and Python programming.",
                        R.drawable.robobanner2
                    )
                    1 -> showProductInfo(
                        "Trishul",
                        "Experience the thrill of rocketry with Trishul — precision model rocket for enthusiasts and learners. Built with quality materials, it offers safe, reliable launches. Ideal for STEM projects and educational demos. Easy assembly and great for hands-on aerospace training.",
                        R.drawable.trishul_new
                    )
                    2 -> showProductInfo(
                        "Drone",
                        "Unlock the skies with our beginner-friendly Drone Kit, designed to ignite curiosity and hands-on learning. Ideal for students and educators, offers stable flight and easy control. Learn aerodynamics and electronics with it.",
                        R.drawable.drone_new
                    )
                    3 -> showProductInfo(
                        "Wegyanik Kit",
                        "Discover IoT with our all-in-one Components Box — sensors, Wi-Fi boards, and more. Perfect for prototyping and learning with Arduino, Raspberry Pi. Build smart devices and automation projects easily.",
                        R.drawable.wegyanik_new_kit
                    )
                }
            }
        })

        // Initialize product adapter with click listener (launch URLs)
        productAdapter = HomeProductAdapter(mutableListOf()) { url ->
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "URL not available", Toast.LENGTH_SHORT).show()
            }
        }

        // Initialize project adapter with click listener (show toast or open details)
        projectAdapter = HomeProjectAdapter(mutableListOf()) { project ->
            Toast.makeText(requireContext(), "Clicked: ${project.title}", Toast.LENGTH_SHORT).show()
        }

        // Fetch product data asynchronously
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                if (response.isSuccessful) {
                    val products = response.body()?.data ?: emptyList()
                    productAdapter.updateData(products)
                } else {
                    Log.e("API", "Error fetching products response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception fetching products: ${e.localizedMessage}")
            }
        }

        // Fetch projects data asynchronously
        lifecycleScope.launch {
            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    val projects = response.body() ?: emptyList()
                    projectAdapter.updateData(projects)
                } else {
                    Log.e("API", "Error fetching projects response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception fetching projects: ${e.localizedMessage}")
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showProductInfo(title: String, description: String, imageResId: Int) {
        val dialogView = layoutInflater.inflate(R.layout.product_info_dialog, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.productImage)
        val titleView = dialogView.findViewById<TextView>(R.id.productTitle)
        val descView = dialogView.findViewById<TextView>(R.id.productDescription)

        imageView.setImageResource(imageResId)
        titleView.text = title
        descView.text = description

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialog.setOnShowListener {
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        dialog.show()
    }
}
