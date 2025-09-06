package com.example.wegyanik

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

        titleView.text = project.title
        descView.text = HtmlCompat.fromHtml(project.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        descView.movementMethod = LinkMovementMethod.getInstance() // enables clickable links

        Glide.with(this)
            .load("https://wegyanik.in${project.coverImage}")
            .into(imageView)

        val stepsRecyclerView = view.findViewById<RecyclerView>(R.id.stepsRecyclerView)
        stepsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        stepsRecyclerView.adapter = StepAdapter(project.steps)
    }
}
