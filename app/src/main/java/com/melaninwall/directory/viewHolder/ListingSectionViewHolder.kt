package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class ListingSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
    val seeMoreButton: ImageView =
        itemView.findViewById(R.id.see_more_button) // TODO: 11/05/2021 new to do something with this
    val sectionRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view_list)
}