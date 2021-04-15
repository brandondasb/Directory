package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData

class ListingFragmentPresenter(listingFragmentView: ListingFragmentView) {
    val fragmentView = listingFragmentView
    lateinit var imageUrl: String

    fun loadUi(listingItemData: ListingItemData?) {
        if (listingItemData != null) {
            listingItemData.image.let {
                imageUrl = if (it.isEmpty()) {
                    "No data found"
                } else {
                    it
                }
            }
            fragmentView.displayViews(imageUrl)
        }
    }
}