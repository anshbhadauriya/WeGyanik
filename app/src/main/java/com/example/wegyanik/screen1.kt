package com.example.wegyanik
import android.os.Handler
import android.os.Looper
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
            handler.postDelayed(this, 3000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)

        bannerViewPager = findViewById(R.id.bannerViewPager)

        val images = listOf(
            R.drawable.trishul,
            R.drawable.drone
        )

        adapter = BannerAdapter(images)
        bannerViewPager.adapter = adapter

        handler.post(runnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.post(runnable)
    }
}

