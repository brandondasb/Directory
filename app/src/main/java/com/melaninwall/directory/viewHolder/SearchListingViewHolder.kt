package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class SearchListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = itemView.findViewById(R.id.nameSearchTextView)
    val image: ImageView = itemView.findViewById(R.id.imageSearchImageView)
    val category: TextView = itemView.findViewById(R.id.categorySearchTextView)//could be a list
    var verified: ImageView = itemView.findViewById(R.id.verifiedImageView)
    val city: TextView = itemView.findViewById(R.id.locationSearchTextView)
    val homeRootLayout: View = itemView.findViewById(R.id.searchCardViewLayout)
   // val searchView: SearchView = itemView.findViewById(R.id.action_search)
}