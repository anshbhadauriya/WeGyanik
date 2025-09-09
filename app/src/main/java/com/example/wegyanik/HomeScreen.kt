package com.example.wegyanik

import ProductFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen_ui)

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val userName = intent.getStringExtra("USER_NAME")

        // Load default fragment
        replaceFragment(HomeFragment())
        bottomNav.selectedItemId = R.id.nav_home

        // Bottom nav listener
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_projects -> replaceFragment(ProjectFragment())
                R.id.nav_products -> replaceFragment(ProductFragment())
                R.id.nav_glimpses -> replaceFragment(GlimpsesFragment())
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
