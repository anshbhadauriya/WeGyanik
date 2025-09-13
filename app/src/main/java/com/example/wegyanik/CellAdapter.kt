package com.example.wegyanik
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CellAdapter(private val cells: List<Cell>) : RecyclerView.Adapter<CellAdapter.CellViewHolder>() {

    inner class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cellImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell_image, parent, false)
        return CellViewHolder(view)
    }

    override fun getItemCount(): Int = cells.count { it.type == "text" && it.content.isNotBlank() }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val imageCells = cells.filter { it.type == "text" && it.content.isNotBlank() }
        val cell = imageCells[position]

        val content = cell.content
        if (isBase64(content)) {
            val bitmap = decodeBase64ToBitmap(content)
            holder.imageView.setImageBitmap(bitmap)
        } else {
            Glide.with(holder.imageView.context)
                .load(content) // URL
                .placeholder(R.drawable.image_placeholder_bg)
                .into(holder.imageView)
        }
    }

    private fun isBase64(str: String): Boolean {
        return str.length > 100 && !str.startsWith("http")
    }

    private fun decodeBase64ToBitmap(base64Str: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
