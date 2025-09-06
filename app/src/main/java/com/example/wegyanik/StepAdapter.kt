package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView

class StepAdapter(private val stepList: List<Step>) :
    RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    inner class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepTitle: TextView = itemView.findViewById(R.id.stepTitle)
        val stepDesc: TextView = itemView.findViewById(R.id.stepDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_step, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = stepList[position]
        holder.stepTitle.text = step.title
        holder.stepDesc.text = HtmlCompat.fromHtml(step.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    override fun getItemCount(): Int = stepList.size
}
