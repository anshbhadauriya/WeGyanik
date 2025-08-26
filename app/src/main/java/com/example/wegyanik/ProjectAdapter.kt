package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProjectAdapter(
    private val projects: List<Project>
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.projectTitle)
        val description: TextView = itemView.findViewById(R.id.projectDescription)
        val image: ImageView = itemView.findViewById(R.id.projectImage)
        val difficulty: TextView = itemView.findViewById(R.id.projectDifficulty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.title.text = project.title
        holder.description.text = project.description
        holder.difficulty.text = "${project.difficulty} • ${project.duration} • ${project.cost}"

        Glide.with(holder.itemView.context)
            .load("https://wegyanik.in" + project.coverImage)
            .placeholder(R.drawable.image_placeholder_bg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

        // Handle clicks based on position
        holder.itemView.setOnClickListener {
            val activity = holder.itemView.context as AppCompatActivity
            when (position) {
                0 -> {
                    val fragment = InstallationForMACFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                1 -> {
                    val fragment = InstallationForWindowsFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                2 -> {
                    val fragment = IotTutorial()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                else -> {
                    // For other projects, just open YouTube
                    val intent = android.content.Intent(
                        android.content.Intent.ACTION_VIEW,
                        android.net.Uri.parse(project.videoUrl)
                    )
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = projects.size
}
