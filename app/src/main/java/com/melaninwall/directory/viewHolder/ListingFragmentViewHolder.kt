package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.melaninwall.directory.R

class ListingFragmentViewHolder(itemView: View) {
    val name: TextView = itemView.findViewById(R.id.listingNameTextView)
    val image: ImageView = itemView.findViewById(R.id.listingImageView)
    val verified: ImageView = itemView.findViewById(R.id.listingVerifiedListingImageView)
    val category: TextView = itemView.findViewById(R.id.listingCategoryTextView)
    val tabLayout: TabLayout = itemView.findViewById(R.id.detailsTablayout)
    val viewPager: ViewPager2 = itemView.findViewById(R.id.itemDetailPager)
}
