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
        val videoWebView = view.findViewById<WebView>(R.id.videoWebView)
        val esp32WebView = view.findViewById<WebView>(R.id.esp32WebView)

        // YouTube video IDs
        val videoId = "-VBw0u68t_E"
        val videoId2 = "wqVCJVoiHNc"
        val esp32Url = "https://www.youtube.com/watch?v=wG-qOnQwv7U"

        // HTML for first video
        val htmlData = """
            <html>
            <body style="margin:0;padding:0;">
            <a href="https://www.youtube.com/watch?v=$videoId" target="_blank">
                <img src="https://img.youtube.com/vi/$videoId/0.jpg" style="width:100%;height:100%;"/>
            </a>
            </body>
            </html>
        """

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

        // HTML for ESP32 video
        val esp32Html = """
            <html>
            <body style="margin:0;padding:0;">
            <a href="$esp32Url" target="_blank">
                <img src="https://img.youtube.com/vi/wG-qOnQwv7U/0.jpg" style="width:100%;height:100%;"/>
            </a>
            </body>
            </html>
        """

        // Load thumbnails
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null)
        videoWebView.loadDataWithBaseURL(null, htmlData2, "text/html", "utf-8", null)
        esp32WebView.loadDataWithBaseURL(null, esp32Html, "text/html", "utf-8", null)

        // Fallback click listeners
        webView.setOnClickListener {
            val url = "https://www.youtube.com/watch?v=$videoId"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        videoWebView.setOnClickListener {
            val url = "https://www.youtube.com/watch?v=$videoId2"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        esp32WebView.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(esp32Url)))
        }
        val esp32BlinkUrl = "https://www.youtube.com/watch?v=I8fPYF6Gbwk"
        val esp32BlinkHtml = """
    <html>
    <body style="margin:0;padding:0;">
    <a href="$esp32BlinkUrl" target="_blank">
        <img src="https://img.youtube.com/vi/I8fPYF6Gbwk/0.jpg" style="width:100%;height:100%;"/>
    </a>
    </body>
    </html>
"""
        val esp32BlinkWebView = view.findViewById<WebView>(R.id.esp32BlinkWebView)
        esp32BlinkWebView.loadDataWithBaseURL(null, esp32BlinkHtml, "text/html", "utf-8", null)

        esp32BlinkWebView.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(esp32BlinkUrl)))
        }

    }
}
