package com.example.wegyanik

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // ✅ Load Home (screen1) fragment by default
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, home_Screen())
            .commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.a)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, home_Screen())
                        .commit()
                    true
                }

                R.id.nav_explore -> {
                    Toast.makeText(this, "Explore clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_products -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, shop())
                        .commit()
                    true
                }

                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}
