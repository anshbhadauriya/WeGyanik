package com.example.wegyanik

import retrofit2.Response
import retrofit2.http.GET

interface ProjectApiService {
    @GET("api/projects")
    suspend fun getProjects(): Response<ProjectResponse>
}
