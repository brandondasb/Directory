package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.adapters.ListingPagerAdapter
import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.PagerSection
import com.melaninwall.directory.presenter.ListingFragmentPresenter
import com.melaninwall.directory.viewHolder.ListingFragmentViewHolder

class ListingFragment : NestedFragment(), ListingFragmentView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val data = bundle?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        savedInstanceState?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData
        ListingFragmentPresenter(this).loadUi(data)
    }

    override fun displayViews(imageUrl: String) {

        val args = arguments?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        args as ListingItemData
        val viewHolder = view?.let {
            ListingFragmentViewHolder(it)
        }

        if (viewHolder != null) {
            view?.context?.let {
                Glide.with(it)
                    .load(imageUrl)
                    .centerCrop()
                    .into(viewHolder.image)
            }

            val titles = PagerSection.values().toList()
            val adapter = ListingPagerAdapter(args)
            viewHolder.viewPager.adapter = adapter

            TabLayoutMediator(viewHolder.tabLayout, viewHolder.viewPager) { tab, position ->
                tab.text = titles[position].name
            }.attach()

            viewHolder.viewPager.clipChildren
            viewHolder.viewPager.offscreenPageLimit = 1
        }
    }
}
