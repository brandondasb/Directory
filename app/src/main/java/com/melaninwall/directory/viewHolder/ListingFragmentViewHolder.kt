package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.melaninwall.directory.R

class ListingFragmentViewHolder(itemView: View) {
    val name: TextView = itemView.findViewById(R.id.fpaNameTextView)
    val image: ImageView = itemView.findViewById(R.id.fpaImageView)
    val verified: ImageView = itemView.findViewById(R.id.fpaVerifiedListingImageView)
    val category: TextView = itemView.findViewById(R.id.fpaCategoryTextView)

    //  val about: TextView = itemView.findViewById(R.id.fpaAboutTextView)
//    val address: TextView = itemView.findViewById(R.id.fpaAddress)
//    val city: TextView = itemView.findViewById(R.id.fpaCity)
//    val postcode: TextView = itemView.findViewById(R.id.fpaPostcode)
//    val website: TextView = itemView.findViewById(R.id.fpaWebsite)
//    val twitter: TextView = itemView.findViewById(R.id.fpatwitter)
//    val facebook: TextView = itemView.findViewById(R.id.fpaFacebook)
//    val instagram: TextView = itemView.findViewById(R.id.fpaInstagram)
    val tabLayout: TabLayout = itemView.findViewById(R.id.detailsTablayout)
    val viewPager: ViewPager2 = itemView.findViewById(R.id.itemDetailPager)
}
