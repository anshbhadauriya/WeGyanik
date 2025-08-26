package com.example.wegyanik

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeFragment : Fragment(R.layout.activity_home) {

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
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
