package com.example.wegyanik

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch

class ProjectFragment : Fragment(R.layout.fragment_project) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var shimmerLayout: ShimmerFrameLayout

    private val projectApiService = RetrofitInstance.retrofit.create(ProjectApiService::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewProjects)
        shimmerLayout = view.findViewById(R.id.shimmerLayout)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        projectAdapter = ProjectAdapter { project ->
            navigateToProjectDetail(project)
        }

        recyclerView.adapter = projectAdapter

        fetchProjectData()
    }

    private fun navigateToProjectDetail(project: Project) {
        val fragment = ProjectDetailFragment.newInstance(project)
        val activity = requireActivity() as? AppCompatActivity
        if (activity != null) {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            Log.e("ProjectFragment", "Activity is null or not AppCompatActivity")
        }
    }

    private fun showShimmer() {
        shimmerLayout.visibility = View.VISIBLE
        shimmerLayout.startShimmer()
        recyclerView.visibility = View.GONE
    }

    private fun hideShimmer() {
        shimmerLayout.stopShimmer()
        shimmerLayout.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun fetchProjectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            showShimmer()
            try {
                val response = projectApiService.getProjects()
                if (response.isSuccessful) {
                    val projects = response.body()?.projects ?: emptyList()
                    if (projects.isEmpty()) {
                        Toast.makeText(requireContext(), "No projects found", Toast.LENGTH_SHORT).show()
                    }
                    ProjectRepository.saveProjects(projects)
                    projectAdapter.submitList(projects)
                    Log.d("ProjectFragment", "Fetched projects from API")
                } else {
                    Log.e("ProjectFragment", "API error: ${response.code()} ${response.message()}")
                    Toast.makeText(requireContext(), "Failed to load projects (code: ${response.code()})", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e("ProjectFragment", "Error fetching projects", e)
                Toast.makeText(requireContext(), "Error loading projects: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            } finally {
                hideShimmer()
            }
        }
    }
}
