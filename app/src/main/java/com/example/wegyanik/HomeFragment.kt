package com.example.wegyanik

import ProductFragment
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment(R.layout.activity_home) {

//    private lateinit var productAdapter: HomeProductAdapter
//    private lateinit var projectAdapter: HomeProjectAdapter
//    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

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

        val cardProducts = view.findViewById<MaterialCardView>(R.id.productsCard)
        val cardProjects = view.findViewById<MaterialCardView>(R.id.projectsCard)
        val cardInternships = view.findViewById<MaterialCardView>(R.id.internshipsCard)
        val cardCompetitions = view.findViewById<MaterialCardView>(R.id.competitionsCard)
        val internship=view.findViewById<LinearLayout>(R.id.internshipsCard1)
        val competition=view.findViewById<LinearLayout>(R.id.competitionsCard1)
        val wegyanik_for_students=view.findViewById<TextView>(R.id.wegyanik_for_students)
        val wegyanik_for_academia_industry=view.findViewById<TextView>(R.id.wegyanik_for_academia_industry)
        wegyanik_for_academia_industry.setOnClickListener { openFragment(UpComingFragment()) }

        wegyanik_for_students.setOnClickListener { openFragment(UpComingFragment()) }

        internship.setOnClickListener { openFragment(UpComingFragment()) }
        competition.setOnClickListener { openFragment(UpComingFragment()) }

        cardProducts.setOnClickListener { openFragment(ProductFragment()) }
        cardProjects?.setOnClickListener { openFragment(ProjectFragment()) }
        cardInternships?.setOnClickListener {  }
        cardCompetitions?.setOnClickListener {  }

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
