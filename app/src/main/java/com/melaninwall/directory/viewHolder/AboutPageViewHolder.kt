package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class AboutPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val about: TextView = itemView.findViewById(R.id.fpaAboutTextView)
}