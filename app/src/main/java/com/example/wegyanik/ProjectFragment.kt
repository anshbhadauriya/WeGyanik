package com.example.wegyanik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProjectFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProjectAdapter
    private val projectList = mutableListOf<Project>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewProjects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadProjects()
        adapter = ProjectAdapter(projectList)
        recyclerView.adapter = adapter

        return view
    }

    private fun loadProjects() {
        projectList.add(
            Project(
                title = "Arduino IDE Installation for MAC",
                description = "Learn how to install the Arduino IDE on your MAC",
                coverImage = "/api/images/1751696215267-Wegyanik.png",
                difficulty = "Beginner",
                duration = "10 min",
                cost = "0",
                videoUrl = "https://www.youtube.com/watch?v=xmGVYPbQqYk&t=1s&ab_channel=Wegyanik"
            )
        )
        projectList.add(
            Project(
                title = "Arduino IDE Installation for Windows",
                description = "Learn how to install the Arduino IDE on your Windows PC",
                coverImage = "/api/images/1751694231249-Snapshot_7.PNG",
                difficulty = "Beginner",
                duration = "10 Minutes",
                cost = "0",
                videoUrl = "https://www.youtube.com/watch?v=CIUAcUKC5k8&ab_channel=Wegyanik"
            )
        )
        projectList.add(
            Project(
                title = "IOT Basic KIT Tutorial",
                description = "Exploring everything about the Wegyanik Basic Kit.",
                coverImage = "/api/images/1751023991812-4894c7a4-d807-4bfb-972f-c025843e2cc9.jpg",
                difficulty = "Beginner",
                duration = "48 Hours",
                cost = "1600",
                videoUrl = "https://www.youtube.com/watch?v=-VBw0u68t_E&ab_channel=Wegyanik"
            )
        )
    }
}