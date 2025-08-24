package com.example.wegyanik

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes

class Screen1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize ImageSlider
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)

        // Prepare list of images
        val slideModels = arrayListOf(
            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
            SlideModel(R.drawable.robobanner, ScaleTypes.FIT),
            SlideModel(R.drawable.trii, ScaleTypes.FIT),
            SlideModel(R.drawable.drone, ScaleTypes.FIT),
            SlideModel(R.drawable.wegyanik_kit, ScaleTypes.FIT)
        )

        // Set images to slider
        imageSlider.setImageList(slideModels, ScaleTypes.FIT)
    }
}
