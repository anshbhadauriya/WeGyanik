package com.example.wegyanik

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectResponse(
    val projects: List<Project>
) : Parcelable

@Parcelize
data class Project(
    val id: String,
    val title: String,
    val description: String,
    val coverImage: String,
    val difficulty: String,
    val duration: String,
    val cost: String,
    val videoUrl: String? = null,
    val slug: String? = null,
    val steps: List<Step> = emptyList()
) : Parcelable

@Parcelize
data class Step(
    val id: String,
    val tips: String?,
    val cells: List<Cell>?,
    val title: String,
    val duration: String,
    val difficulty: String,
    val description: String
) : Parcelable

@Parcelize
data class Cell(
    val type: String,
    val content: String
) : Parcelable
