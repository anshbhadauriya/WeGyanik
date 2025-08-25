package com.example.wegyanik

import ShopFragment
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen_ui)
//
//        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
//
//        val slideModels = arrayListOf(
//            SlideModel(R.drawable.robobanner2, ScaleTypes.FIT),
//            SlideModel(R.drawable.robobanner, ScaleTypes.FIT),
//            SlideModel(R.drawable.trii, ScaleTypes.FIT),
//            SlideModel(R.drawable.drone, ScaleTypes.FIT),
//            SlideModel(R.drawable.wegyanik_kit, ScaleTypes.FIT),
//        )
//
//        imageSlider.setImageList(slideModels, ScaleTypes.FIT)

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Setup toolbar with no navigation icon (no hamburger)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = null
        toolbar.setOnClickListener {
            replaceFragment(home_Screen())
            bottomNav.selectedItemId = R.id.nav_home
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Load default fragment
        replaceFragment(home_Screen())
        bottomNav.selectedItemId = R.id.nav_home

        // Bottom nav listener
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(home_Screen())
                R.id.nav_explore -> replaceFragment(ProjectFragment())
                R.id.nav_products -> replaceFragment(ShopFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }

    private fun setBottomNavSelectedItem(itemId: Int, bottomNav: BottomNavigationView) {
        if (bottomNav.selectedItemId != itemId) {
            bottomNav.selectedItemId = itemId
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
