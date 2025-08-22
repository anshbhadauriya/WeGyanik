package com.example.wegyanik

import ShopFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Setup toolbar with no navigation icon (no hamburger)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = null
        toolbar.setOnClickListener {
            // Navigate to homepage fragment and update bottom nav selection
            replaceFragment(home_Screen())
            bottomNav.selectedItemId = R.id.nav_home
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Load default fragment
        replaceFragment(home_Screen())
        bottomNav.selectedItemId = R.id.nav_home

        // Bottom navigation listener
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

        // Drawer navigation listener
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
                    setBottomNavSelectedItem(R.id.nav_profile, bottomNav)
                }
                R.id.menu_community -> {
                    replaceFragment(CommunityFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }
                R.id.menu_domain -> {
                    replaceFragment(DomainFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }
                R.id.menu_whatweoffer -> {
                    replaceFragment(WhatWeOfferFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }
                else -> return@setNavigationItemSelectedListener false
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setBottomNavSelectedItem(itemId: Int, bottomNav: BottomNavigationView) {
        if (bottomNav.selectedItemId != itemId) {
            bottomNav.selectedItemId = itemId
        }
    }

    // Utility to replace fragments in the container
    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    // Back press closes drawer if open, otherwise performs default action
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
