package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProjectAdapter(
    private val projectList: MutableList<Project> = mutableListOf(),
    private val onProjectClick: (Project) -> Unit
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
        val project = projectList[position]
        holder.title.text = project.title
        holder.description.text = android.text.Html.fromHtml(project.description) // fallback
        holder.difficulty.text = "${project.difficulty} • ${project.duration} • Rs. ${project.cost}"

        Glide.with(holder.itemView.context)
            .load("https://wegyanik.in" + project.coverImage)
            .placeholder(R.drawable.image_placeholder_bg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onProjectClick(project)
        }
    }

    override fun getItemCount() = projectList.size

    fun updateData(newList: List<Project>) {
        projectList.clear()
        projectList.addAll(newList)
        notifyDataSetChanged()
    }
}
