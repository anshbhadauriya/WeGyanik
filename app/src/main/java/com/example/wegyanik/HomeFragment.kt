package com.example.wegyanik

import ShopFragment
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class HomeFragment : Fragment(R.layout.activity_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup image slider with slide models
//        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
//        val slideModels = arrayListOf(
//            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
//            SlideModel(R.drawable.robobanner, ScaleTypes.FIT),
//            SlideModel(R.drawable.trii, ScaleTypes.FIT),
//            SlideModel(R.drawable.drone, ScaleTypes.FIT),
//            SlideModel(R.drawable.wegyanik_kit, ScaleTypes.FIT)
//        )
//        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        // Setup RecyclerView with Grid Layout for card grid
        val cardGrid = view.findViewById<RecyclerView>(R.id.cardGrid)
        cardGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        cardGrid.adapter = HomeCardAdapter(getHomeCards()) // You need to implement this adapter and data source

        val cards = listOf(
            HomeCard("Internships", "Gain Practical Experience", R.drawable.ic_internship),
            HomeCard("Mentorships", "Guidance from Top Mentors", R.drawable.ic_mentorship),
            HomeCard("Products", "Explore Dream Careers", R.drawable.ic_products),
            HomeCard("Projects", "Build Skills Daily", R.drawable.ic_projects),
            HomeCard("Competitions", "Test for Excellence", R.drawable.ic_competitions),
//            HomeCard("More", "Explore Additional Options", R.drawable.ic_more)
        )

        cardGrid.adapter = HomeCardAdapter(cards)


        // Setup search bar text changed listener to filter cards
        val searchEditText = view.findViewById<TextInputEditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener { text ->
            // TODO: Add filter logic in your adapter
            cardGrid.adapter?.let {
                if (it is FilterableAdapter) {
                    it.filter(text.toString())
                }
            }
        }

        // Setup bottom navigation item selection
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // stay here
                R.id.nav_projects -> { openFragment(ProjectFragment()); true }
                R.id.nav_products -> { openFragment(ShopFragment()); true }
//                R.id.nav_compete -> { openFragment(CompetitionFragment()); true }
                R.id.nav_profile -> { openFragment(ProfileFragment()); true }
                else -> false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Dummy placeholder to simulate cards data retrieval
    private fun getHomeCards(): List<HomeCard> {
        // Return list of cards here
        return listOf()
    }
}
