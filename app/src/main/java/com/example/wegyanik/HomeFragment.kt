package com.example.wegyanik

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment(R.layout.activity_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)

        val slideModels = arrayListOf(
            SlideModel(R.drawable.new_dog, ScaleTypes.FIT),
            SlideModel(R.drawable.trishul_new, ScaleTypes.FIT),
            SlideModel(R.drawable.drone_new, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_new_kit, ScaleTypes.FIT)
        )

        val textView = view.findViewById<MaterialTextView>(R.id.gradientTextView)
        val text = textView.text.toString()
        val width = textView.paint.measureText(text)

        val shader = LinearGradient(
            0f, 0f, width, 0f,
            intArrayOf(
                Color.parseColor("#02BC6A"),  // Green for "Where Innovation"
                Color.parseColor("#5DACFA")   // Blue for "meets Creation"
            ),
            null,
            Shader.TileMode.CLAMP
        )

        textView.paint.shader = shader
        textView.invalidate()

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                when (position) {
                    0 -> showProductInfo(
                        "Vaman",
                        "Meet our Robotic Dog — a smart, agile and interactive four-legged companion built to mimic real canine behavior. Designed for education, exploration, and entertainment, this robotic pet can walk, sit, respond to voice commands, and express emotions via LED eyes. Powered by precise servo motors and sensors, it navigates and avoids obstacles. Perfect for students and hobbyists, supports Arduino and Python programming.",
                        R.drawable.new_dog
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

        val cardInternships = view.findViewById<MaterialCardView>(R.id.internshipsCard)
        val cardWorkshops = view.findViewById<MaterialCardView>(R.id.workshopsCard)
        val cardProjects = view.findViewById<MaterialCardView>(R.id.projectsCard)
        val cardOpportunities = view.findViewById<MaterialCardView>(R.id.opportunitiesCard)
        val cardJobs = view.findViewById<MaterialCardView>(R.id.jobsCard)
        val cardWegyanik = view.findViewById<MaterialCardView>(R.id.wegyanikCard)
        val wegyanik_for_students=view.findViewById<TextView>(R.id.wegyanik_for_students)
        val wegyanik_for_academia_industry=view.findViewById<TextView>(R.id.wegyanik_for_academia_industry)

        cardInternships?.setOnClickListener { openFragment(UpComingFragment()) }
        cardWorkshops?.setOnClickListener { openFragment(UpComingFragment()) }
        cardProjects?.setOnClickListener { openFragment(ProjectFragment()) }
        cardOpportunities?.setOnClickListener { openFragment(UpComingFragment()) }
        cardJobs?.setOnClickListener { openFragment(UpComingFragment()) }
        cardWegyanik?.setOnClickListener { openFragment(UpComingFragment()) }
        wegyanik_for_students?.setOnClickListener { openFragment(UpComingFragment()) }
        wegyanik_for_academia_industry?.setOnClickListener { openFragment(UpComingFragment()) }
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
