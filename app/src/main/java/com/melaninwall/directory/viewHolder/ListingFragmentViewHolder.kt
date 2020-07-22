package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.melaninwall.directory.R

class ListingFragmentViewHolder(itemView: View) {
    val name: TextView = itemView.findViewById(R.id.fpaNameTextView)
    val about: TextView = itemView.findViewById(R.id.fpaAboutTextView)
    val image: ImageView = itemView.findViewById(R.id.fpaImageView)
    val category: TextView = itemView.findViewById(R.id.fpaCategoryTextView)
    val address: TextView = itemView.findViewById(R.id.fpaAddress)
    val city: TextView = itemView.findViewById(R.id.fpaCity)
    val postcode: TextView = itemView.findViewById(R.id.fpaPostcode)
    val website: TextView = itemView.findViewById(R.id.fpaWebsite)
    val twitter: TextView = itemView.findViewById(R.id.fpatwitter)
    val facebook: TextView = itemView.findViewById(R.id.fpaFacebook)
    val instagram: TextView = itemView.findViewById(R.id.fpaInstagram)
    val verified: ImageView = itemView.findViewById(R.id.fpaVerifiedListingImageView)


}