package com.example.wegyanik

object ProjectRepository {
    private var cachedProjects: List<Project>? = null

    fun getCachedProjects(): List<Project>? = cachedProjects

    fun saveProjects(projects: List<Project>) {
        cachedProjects = projects
    }

    fun clearCache() {
        cachedProjects = null
    }
}
