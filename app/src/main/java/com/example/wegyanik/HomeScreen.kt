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
//    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen_ui)

        // Initialize views

        drawerLayout = findViewById(R.id.drawerLayout)
//        toolbar = findViewById(R.id.toolbar)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val userName = intent.getStringExtra("USER_NAME")




        // Setup toolbar with no navigation icon (no hamburger)
//        setSupportActionBar(toolbar)
//        toolbar.navigationIcon = null
//        toolbar.setOnClickListener {
//            replaceFragment(home_Screen())
//            bottomNav.selectedItemId = R.id.nav_home
//        }
//        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Load default fragment

        replaceFragment(home_Screen())
        bottomNav.selectedItemId = R.id.nav_home

        // Bottom nav listener
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(home_Screen())
                R.id.nav_projects -> replaceFragment(ProjectFragment())
                R.id.nav_products -> replaceFragment(ShopFragment())
                R.id.nav_profile ->  {
                    val profileFragment = ProfileFragment()
                    val bundle = Bundle()
                    bundle.putString("USER_NAME", userName)
                    profileFragment.arguments = bundle
                    replaceFragment(profileFragment)
                }
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
