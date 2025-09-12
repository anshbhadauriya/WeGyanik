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
    private val projectApiService by lazy { RetrofitInstance.retrofit.create(ProjectApiService::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewProjects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        projectAdapter = ProjectAdapter { project ->
            val fragment = ProjectDetailFragment.newInstance(project)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = projectAdapter

        fetchProjectData()
    }

    private fun fetchProjectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    response.body()?.projects?.let { projects ->
                        projectAdapter.submitList(projects)
                    }
                } else {
                    Log.e("ProjectFragment", "Failed to fetch projects: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProjectFragment", "Error fetching projects", e)
            }
        }
    }
}
