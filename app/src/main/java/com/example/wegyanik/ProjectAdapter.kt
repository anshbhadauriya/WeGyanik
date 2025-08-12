import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.wegyanik.Project
import com.example.wegyanik.R

class ProjectAdapter(private val projects: List<Project>) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.projectTitle)
        val description: TextView = itemView.findViewById(R.id.projectDescription)
        val image: ImageView = itemView.findViewById(R.id.projectImage)
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

        // If thumbnailUrl from API starts with "/uploads/..."
        Glide.with(holder.itemView.context)
            .load("https://wegyanik.in" + project.coverImage)
            .placeholder(R.drawable.image_placeholder_bg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)  // Cache both original & resized
            .into(holder.image)
    }

    override fun getItemCount() = projects.size
}
