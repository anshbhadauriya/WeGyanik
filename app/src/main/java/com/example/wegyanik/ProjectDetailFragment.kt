package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import android.webkit.WebView

class ProjectDetailFragment : Fragment(R.layout.fragment_project_detail) {

    companion object {
        private const val ARG_PROJECT = "project"

        fun newInstance(project: Project): ProjectDetailFragment {
            val fragment = ProjectDetailFragment()
            val bundle = Bundle().apply {
                putParcelable(ARG_PROJECT, project)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        project = requireArguments().getParcelable(ARG_PROJECT)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val titleView = view.findViewById<TextView>(R.id.title)
        val descView = view.findViewById<TextView>(R.id.description)
        val imageView = view.findViewById<ImageView>(R.id.projectImage)
        val videoWebView = view.findViewById<WebView>(R.id.videoWebView)
        val btnOpenYouTube = view.findViewById<MaterialButton>(R.id.btnOpenYouTube)

        titleView.text = project.title
        descView.text = HtmlCompat.fromHtml(project.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        descView.movementMethod = LinkMovementMethod.getInstance() // enables clickable links

        Glide.with(this)
            .load("https://wegyanik.in${project.coverImage}")
            .into(imageView)

        val youtubeUrl = project.extractYouTubeUrl()
        if (!youtubeUrl.isNullOrEmpty()) {
            videoWebView.visibility = View.VISIBLE
            btnOpenYouTube.visibility = View.VISIBLE

            // Enable JavaScript for YouTube player
            videoWebView.settings.javaScriptEnabled = true
            videoWebView.settings.domStorageEnabled = true

            // Build the embed URL for iframe
            val videoId = extractYouTubeVideoId(youtubeUrl)
            val embedHtml = """
            <html>
                <body style="margin:0;padding:0;">
                    <iframe width="100%" height="100%" 
                    src="https://www.youtube.com/embed/$videoId" 
                    frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
                    </iframe>
                </body>
            </html>
        """.trimIndent()

            videoWebView.loadData(embedHtml, "text/html", "utf-8")

            // Open official YouTube app or browser when button clicked
            btnOpenYouTube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                startActivity(intent)
            }
        } else {
            videoWebView.visibility = View.GONE
            btnOpenYouTube.visibility = View.GONE
        }

        val stepsRecyclerView = view.findViewById<RecyclerView>(R.id.stepsRecyclerView)
        stepsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        stepsRecyclerView.adapter = StepAdapter(project.steps)
    }

    // Helper to extract video ID from full YouTube URL (basic implementation)
    private fun extractYouTubeVideoId(url: String): String? {
        val regex = "v=([a-zA-Z0-9_-]{11})".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value
    }
}
