package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class DiscoverListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //  val id: TextView = itemView.findViewById(R.id.)
    val name: TextView = itemView.findViewById(R.id.nameDiscoverTextView)
    // val about: TextView = itemView.findViewById(R.id.)
    val image: ImageView = itemView.findViewById(R.id.imageDiscoverImageView)
    val category: TextView = itemView.findViewById(R.id.categorySearchTextView)//could be a list
    var verified: ImageView = itemView.findViewById(R.id.verifiedImageView)
    val city: TextView = itemView.findViewById(R.id.locationSearchTextView)
//    val postcode: TextView = itemView.findViewById(R.id.cityTextView)
    val homeRootLayout: View = itemView.findViewById(R.id.searchCardViewLayout)
}