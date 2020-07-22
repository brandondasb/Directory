package com.melaninwall.directory.presenter

import android.view.View
import com.bumptech.glide.Glide
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.ListingFragmentViewHolder
import kotlinx.android.synthetic.main.item_detail_fragment.*

class ListingFragmentPresenter(itemView: View) {
    val view = itemView
    val viewHolder = ListingFragmentViewHolder(view)
    fun loadUi(listingItemData: ListingItemData) {
        val fragment = ListingFragment.create(listingItemData)
        val data = fragment.arguments?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData

        viewHolder.name.text = data.name
        viewHolder.about.text = data.about
        viewHolder.category.text = data.category.joinToString(" | ")
        viewHolder.address.text = data.address
        viewHolder.city.text = data.city
        viewHolder.postcode.text = data.postcode
        viewHolder.website.text = data.website
        viewHolder.twitter.text = data.social.firstOrNull { it.name == "twitter" }?.url
        viewHolder.facebook.text = data.social.firstOrNull { it.name == "facebook" }?.url
        viewHolder.instagram.text = data.social.firstOrNull { it.name == "instagram" }?.url

        if (data.verified) {
            viewHolder.verified.visibility = View.VISIBLE
        } else {
            viewHolder.verified.visibility = View.GONE
        }
        Glide.with(view.context)
            .load(data.image)
            .centerCrop()
            .into(viewHolder.image)
    }
}