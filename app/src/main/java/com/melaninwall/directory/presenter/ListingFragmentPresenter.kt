package com.melaninwall.directory.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.melaninwall.directory.R
import com.melaninwall.directory.R.layout.item_detail_page_gallery
import com.melaninwall.directory.R.layout.item_detail_page_summary
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.model.BuildListingBinder
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.PagerSection
import com.melaninwall.directory.model.lookUpPagerSection
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.GalleryPageViewHolder
import com.melaninwall.directory.viewHolder.ListingFragmentViewHolder
import com.melaninwall.directory.viewHolder.SocialPageViewHolder
import com.melaninwall.directory.viewHolder.SummaryPageViewHolder

class ListingFragmentPresenter(itemView: View) {
    val view = itemView
    val viewHolder = ListingFragmentViewHolder(view)

    private lateinit var adapter: ListingPagerAdapter

    fun loadUi(listingItemData: ListingItemData?) {
        val fragment = ListingFragment.create(listingItemData)
        val data = fragment.arguments?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData

        viewHolder.name.text = data.name
        viewHolder.category.text = data.category.joinToString(" | ")
//        viewHolder.address.text = data.address
//        viewHolder.city.text = data.city
//        viewHolder.postcode.text = data.postcode
//        viewHolder.website.text = data.website

        if (data.verified) {
            viewHolder.verified.visibility = View.VISIBLE
        } else {
            viewHolder.verified.visibility = View.GONE
        }

        Glide.with(view.context)
            .load(data.image)
            .centerCrop()
            .into(viewHolder.image)

        val titles = PagerSection.values().toList()
        adapter = ListingPagerAdapter(data)
        viewHolder.viewPager.adapter = adapter

        TabLayoutMediator(viewHolder.tabLayout, viewHolder.viewPager) { tab, position ->
            tab.text = titles[position].name
        }.attach()

        viewHolder.viewPager.clipChildren
    }

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
                            .inflate(item_detail_page_summary, parent, false)
                    SummaryPageViewHolder(summaryView)
                }
                PagerSection.GALLERY -> {
                    val galleryView =
                        inflater
                            .inflate(item_detail_page_gallery, parent, false)
                    GalleryPageViewHolder(galleryView)
                }
                PagerSection.CONTACT -> {
                    val socialView =
                        inflater
                            .inflate(R.layout.item_detail_page_social, parent, false)
                    SocialPageViewHolder(socialView)
                }
            }
        }

        override fun getItemCount(): Int = PagerSection.values().size

        override fun getItemViewType(position: Int): Int = position

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binder = BuildListingBinder(holder, data)
            binder.bind()
        }
    }
}