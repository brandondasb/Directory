package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class ListingItemGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
    val seeMoreButton: Button = itemView.findViewById(R.id.see_more_button)
    val groupRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view_list)
}