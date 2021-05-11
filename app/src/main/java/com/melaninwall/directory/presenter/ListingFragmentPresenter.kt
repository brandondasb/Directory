package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData

class ListingFragmentPresenter(listingFragmentView: ListingFragmentView) {
    private val fragmentView = listingFragmentView
    lateinit var imageUrl: String

    fun loadUi(listingItemData: ListingItemData?) {
        tryToShowListing(listingItemData)
    }

    private fun tryToShowListing(listingItemData: ListingItemData?) {
        if (listingItemData != null) {
            listingItemData.image.let {
                imageUrl = if (it.isEmpty()) {
                    "https://upload.wikimedia.org/wikipedia/commons/6/6c/No_image_3x4.svg"
                } else {
                    it
                }
            }
            fragmentView.displayViews(imageUrl)
        } else {
            fragmentView.showErrorWith("No listing data available")
        }
    }
}