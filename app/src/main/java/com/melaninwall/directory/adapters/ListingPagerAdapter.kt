package com.melaninwall.directory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.BuildListingBinder
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.PagerSection
import com.melaninwall.directory.model.lookUpPagerSection
import com.melaninwall.directory.viewHolder.*

//TODO pager set up
class ListingPagerAdapter(
    val data: ListingItemData?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (lookUpPagerSection(viewType)) {
            PagerSection.SUMMARY -> {
                val summaryView =
                    inflater
                        .inflate(R.layout.item_detail_page_summary, parent, false)
                SummaryPageViewHolder(summaryView)
            }
            PagerSection.ABOUT -> {
                val about =
                    inflater
                        .inflate(R.layout.item_detail_page_about, parent, false)
                AboutPageViewHolder(about)
            }
            PagerSection.GALLERY -> {
                val galleryView =
                    inflater
                        .inflate(R.layout.item_detail_page_gallery, parent, false)
                GalleryPageViewHolder(galleryView)
            }
            PagerSection.DETAILS -> {
                val socialView =
                    inflater
                        .inflate(R.layout.item_detail_page_details, parent, false)
                DetailPageViewHolder(socialView)
            }
        }
    }

    override fun getItemCount(): Int = PagerSection.values().size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pageViewGenerator = holder as PageViewGenerator
        val binder = BuildListingBinder(pageViewGenerator, data)
        binder.bind() //getS called twice would like to know why
    }
}