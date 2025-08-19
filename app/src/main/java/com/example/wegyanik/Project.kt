package com.example.wegyanik

import com.google.gson.annotations.SerializedName

data class Project(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    @SerializedName("cover_image") val coverImage: String = "",
    val difficulty: String = "",
    val duration: String = "",
    val cost: String = "",
    @SerializedName("video_url") val videoUrl: String = ""
)
