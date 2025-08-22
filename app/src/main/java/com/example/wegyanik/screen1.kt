package com.example.wegyanik

import android.os.Handler
import android.os.Looper
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class screen1 : AppCompatActivity() {
    private lateinit var bannerViewPager: ViewPager2
    private lateinit var adapter: BannerAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    private val runnable = object : Runnable {
        override fun run() {
            if (currentPage == adapter.itemCount) {
                currentPage = 0
            }
            bannerViewPager.setCurrentItem(currentPage++, true)
            handler.postDelayed(this, 15000) // 15 seconds delay
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bannerViewPager = findViewById(R.id.bannerViewPager)


        // List of images to show in banner carousel
        val images = listOf(
            R.drawable.robobanner,
            R.drawable.robobanner2,
            R.drawable.trishul,
            R.drawable.drone,
            R.drawable.vamann,
            R.drawable.wegyanik_kit,
            R.drawable.wegyanik_kit2
        )
        adapter = BannerAdapter(images)
        Log.d("screen1", "Before setting adapter")
        bannerViewPager.adapter = adapter
        Log.d("screen1", "After setting adapter")

        // Start automatic sliding
        handler.post(runnable)
    }

    override fun onPause() {
        super.onPause()
        // Stop auto sliding to prevent memory leak
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        // Resume auto sliding
        handler.post(runnable)
    }
}