package com.example.wegyanik

fun Project.extractYouTubeUrl(): String? {
    steps.forEach { step ->
        step.cells?.forEach { cell ->
            val url = cell.content
                .replace(Regex("<.*?>"), "") // Remove HTML tags like <p>
                .trim()
            if (url.startsWith("https://www.youtube.com/") || url.startsWith("https://youtu.be/")) {
                return url
            }
        }
    }
    return null
}
