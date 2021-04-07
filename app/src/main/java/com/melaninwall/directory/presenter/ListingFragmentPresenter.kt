package com.melaninwall.directory.presenter

import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.adapters.ListingPagerAdapter
import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.PagerSection
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.ListingFragmentViewHolder

class ListingFragmentPresenter(itemView: View, listingFragmentView: ListingFragmentView) {
    val view = itemView
    val viewHolder = ListingFragmentViewHolder(view)
    val fragmentView = listingFragmentView

    private lateinit var adapter: ListingPagerAdapter

    fun loadUi(listingItemData: ListingItemData?) {
        val fragment = ListingFragment.create(listingItemData)
        val data = fragment.arguments?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData

        viewHolder.name.text = data.name
        viewHolder.category.text = data.category.joinToString(" | ")

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
        viewHolder.viewPager.offscreenPageLimit = 1
    }

}