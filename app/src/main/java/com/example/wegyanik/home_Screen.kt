package com.example.wegyanik

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.launch
import com.denzcoskun.imageslider.interfaces.ItemClickListener


class home_Screen : Fragment(R.layout.activity_home) {

    private lateinit var productAdapter: HomeProductAdapter
    private lateinit var projectAdapter: HomeProjectAdapter

    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)

        val slideModels = arrayListOf(
            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
            SlideModel(R.drawable.trishul_new, ScaleTypes.FIT),
//            SlideModel(R.drawable.wegyanik_kit1, ScaleTypes.FIT),
            SlideModel(R.drawable.drone_new, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_new_kit, ScaleTypes.FIT),
        )

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)


        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                when (position) {
                    0 -> showProductInfo("Vaman", "Meet our Robotic Dog — a smart, agile, and interactive four-legged companion built to mimic real canine behavior. Designed for education, exploration, and entertainment, this robotic pet can walk, sit, respond to voice commands, and even express emotions through LED eyes. Powered by precise servo motors and intelligent sensors, it navigates its environment, avoids obstacles, and follows its owner. Whether you're learning robotics, coding, or just love futuristic gadgets, this robotic dog offers hands-on experience in AI and mechanical design. It supports programming via Arduino or Python, making it perfect for students, hobbyists, and developers. With a durable frame and responsive mobility, it's not just a toy — it's a technological marvel. Bring home your own robotic companion and explore the future of pet robotics.", R.drawable.robobanner2)
                    1 -> showProductInfo("Trishul", "Experience the thrill of rocketry with Trishul, our precision-engineered model rocket designed for enthusiasts and learners alike. Built with high-grade materials and aerodynamic stability, Trishul offers safe and reliable launches every time. Perfect for educational demonstrations, STEM projects, and hands-on aerospace training. Easy to assemble and launch, it brings real-world aerospace principles to life. Take your first step into the world of model rocketry with Trishul—where learning meets excitement.", R.drawable.trishul_new)
//                    2 -> showProductInfo("Product 3", "Description of Product 3", R.drawable.trishul_new)
                    2 -> showProductInfo("Drone", "Unlock the skies with our beginner-friendly Drone Kit, designed to ignite curiosity and hands-on learning. Equipped with essential components for stable flight and easy control, it’s perfect for students, hobbyists, and educators. Learn the fundamentals of aerodynamics, electronics, and remote navigation. The modular design makes assembly and troubleshooting a breeze. Whether for indoor experiments or outdoor exploration, this kit offers a complete introduction to the world of drones.", R.drawable.drone_new)
                    3 -> showProductInfo("Wegyanik Kit", "Unlock the world of Internet of Things with our all-in-one IoT Components Box. Packed with essential modules like sensors, Wi-Fi boards, actuators, and more, it's perfect for building smart devices and automation projects. Whether you're a student, hobbyist, or developer, this kit simplifies prototyping and learning. Compatible with Arduino, Raspberry Pi, and other microcontrollers, it empowers innovation and experimentation. Ideal for home automation, environment monitoring, and connected systems. Start building the future today!", R.drawable.wegyanik_new_kit)
                }
            }
        })


        // Initialize product adapter with click listener
        productAdapter = HomeProductAdapter(mutableListOf()) { url ->
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "URL not available", Toast.LENGTH_SHORT).show()
            }
        }

        // Initialize project adapter (add click listener if desired)
        projectAdapter = HomeProjectAdapter(mutableListOf()) { project ->
            // Handle project click if needed (e.g., open project details)
            Toast.makeText(requireContext(), "Clicked: ${project.title}", Toast.LENGTH_SHORT).show()
        }

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productsRecyclerView.adapter = productAdapter

//        val projectsRecyclerView = view.findViewById<RecyclerView>(R.id.projectsRecyclerView)
//        projectsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        projectsRecyclerView.adapter = projectAdapter

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
                    val projectResponse = response.body()
                    val projects = projectResponse ?: emptyList()
                    projectAdapter.updateData(projects)
                } else {
                    Log.e("API", "Error fetching projects response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception fetching projects: ${e.localizedMessage}")
            }
        }
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
            val okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        }

        dialog.show()

    }


}
