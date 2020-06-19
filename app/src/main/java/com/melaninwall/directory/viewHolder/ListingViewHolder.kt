package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class ListingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //  val id: TextView = itemView.findViewById(R.id.)
    val name: TextView = itemView.findViewById(R.id.nameTextView)
    // val about: TextView = itemView.findViewById(R.id.)
    val image: ImageView = itemView.findViewById(R.id.homeImageView)
    val category: TextView = itemView.findViewById(R.id.categoryTextView)//could be a list
    //val social: TextView/* could change in future*/
    ///val website: TextView = TODO
    //val address: TextView=
    //var verified: TextView =
    //  val city:TextView=
    val postcode: TextView = itemView.findViewById(R.id.postcodeTextView)
    val homeRootLayout: View = itemView.findViewById(R.id.homeCardViewLayout)

}