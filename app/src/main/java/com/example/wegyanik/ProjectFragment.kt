package com.example.wegyanik

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ProjectFragment : Fragment(R.layout.fragment_project) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var projectAdapter: ProjectAdapter
    private val projectList = mutableListOf<Project>()

    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewProjects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        projectAdapter = ProjectAdapter(projectList) { project ->
            val fragment = ProjectDetailFragment.newInstance(project)
            val activity = requireActivity() as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = projectAdapter

        fetchProjectData()
    }

    private fun fetchProjectData() {
        viewLifecycleOwner.lifecycleScope.launch {

            val cached = ProjectRepository.getCachedProjects()
            if (cached != null && cached.isNotEmpty()) {
                projectAdapter.updateData(cached)
                Log.d("ProjectFragment", "Loaded projects from cache")
                return@launch
            }

            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    val projects = response.body()?.projects ?: emptyList()


                    ProjectRepository.saveProjects(projects)

                    // Update adapter
                    projectAdapter.updateData(projects)
                    Log.d("ProjectFragment", "Fetched projects from API")
                } else {
                    Log.e("ProjectFragment", "Failed to fetch projects: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProjectFragment", "Error fetching projects", e)
            }
        }
    }
}
