package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment

class InstallationForWindowsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_installation_for__windows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById<WebView>(R.id.webView)

        val videoId = "CIUAcUKC5k8"
        val htmlData = """
            <html>
            <body style="margin:0;padding:0;">
                <a href="https://www.youtube.com/watch?v=$videoId" target="_blank">
                    <img src="https://img.youtube.com/vi/$videoId/0.jpg" 
                         style="width:100%;height:100%;"/>
                </a>
            </body>
            </html>
        """

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null)

        // Optional: fallback click → open YouTube app
        webView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
            startActivity(intent)
        }
    }
}
