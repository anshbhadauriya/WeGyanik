package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment

class IotTutorial : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_iot_turtorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById<WebView>(R.id.webView)
        val videowebview=view.findViewById<WebView>(R.id.videoWebView)

        // YouTube video ID for IoT Tutorial
        val videoId = "-VBw0u68t_E"
        val videoId2="wqVCJVoiHNc"

        // HTML to show YouTube thumbnail
        val htmlData = """
            <html>
            <body style="margin:0;padding:0;">
            <a href="https://www.youtube.com/watch?v=$videoId" target="_blank">
                <img src="https://img.youtube.com/vi/$videoId/0.jpg" style="width:100%;height:100%;"/>
            </a>
            </body>
            </html>
        """

        // Load thumbnail in WebView
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null)

        // HTML for second video
        val htmlData2 = """
    <html>
    <body style="margin:0;padding:0;">
    <a href="https://www.youtube.com/watch?v=$videoId2" target="_blank">
        <img src="https://img.youtube.com/vi/$videoId2/0.jpg" style="width:100%;height:100%;"/>
    </a>
    </body>
    </html>
"""

// Load thumbnail in second WebView
        videowebview.loadDataWithBaseURL(null, htmlData2, "text/html", "utf-8", null)


        // Optional: fallback click listener
        webView.setOnClickListener {
            val url = "https://www.youtube.com/watch?v=$videoId"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        videowebview.setOnClickListener {
            val url = "https://www.youtube.com/watch?v=$videoId2"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
