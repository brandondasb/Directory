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
//                    "No data found"
                    "https://upload.wikimedia.org/wikipedia/commons/6/6c/No_image_3x4.svg"
                } else {
                    it
                }
            }
            fragmentView.displayViews(imageUrl)
        }
    }
}