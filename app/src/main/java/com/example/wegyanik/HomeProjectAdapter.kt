package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeProjectAdapter(private val projects: MutableList<Project>) :
    RecyclerView.Adapter<HomeProjectAdapter.HomeProjectViewHolder>() {

    class HomeProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.projectImage)
        val title: TextView = itemView.findViewById(R.id.projectTitle)
        val description: TextView = itemView.findViewById(R.id.projectDescription)
        val difficulty: TextView = itemView.findViewById(R.id.projectDifficulty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_project, parent, false)
        return HomeProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.title.text = project.title
        holder.description.text = project.description
        holder.difficulty.text = "${project.difficulty} • ${project.duration} • ${project.cost}"

        val imageUrl = "https://wegyanik.in${project.coverImage}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)
    }

    override fun getItemCount(): Int = projects.size

    fun updateData(newList: List<Project>) {
        projects.clear()
        projects.addAll(newList)
        notifyDataSetChanged()
    }
}
