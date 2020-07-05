package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = itemView.findViewById(R.id.iconNameTextView)
    val image: ImageView = itemView.findViewById(R.id.iconImageView)
    val homeRootLayout: View = itemView.findViewById(R.id.categoryCardViewLayout)
}