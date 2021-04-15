package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.melaninwall.directory.R

class ListingFragmentViewHolder(itemView: View) {

    val image: ImageView = itemView.findViewById(R.id.listingImageView)
    val tabLayout: TabLayout = itemView.findViewById(R.id.detailsTablayout)
    val viewPager: ViewPager2 = itemView.findViewById(R.id.itemDetailPager)
}
