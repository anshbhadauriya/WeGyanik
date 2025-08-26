package com.example.wegyanik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileOptionsAdapter(
    private val options: List<ProfileOption>,
    private val onClick: (ProfileOption) -> Unit,
    private val onToggleChanged: (ProfileOption, Boolean) -> Unit
) : RecyclerView.Adapter<ProfileOptionsAdapter.OptionViewHolder>() {

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.optionTitle)
        private val toggle: Switch = itemView.findViewById(R.id.optionToggle)

        fun bind(option: ProfileOption) {
            title.text = option.title
            if (option.isToggle) {
                toggle.visibility = View.VISIBLE
                toggle.isChecked = option.toggleState
                toggle.setOnCheckedChangeListener { _, isChecked ->
                    onToggleChanged(option, isChecked)
                }
                itemView.setOnClickListener(null)
            } else {
                toggle.visibility = View.GONE
                itemView.setOnClickListener {
                    onClick(option)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(options[position])
    }

    override fun getItemCount(): Int = options.size
}
