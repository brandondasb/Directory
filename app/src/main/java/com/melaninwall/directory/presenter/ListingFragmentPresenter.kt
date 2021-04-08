package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData

class ListingFragmentPresenter(listingFragmentView: ListingFragmentView) {
    val fragmentView = listingFragmentView


    fun loadUi(listingItemData: ListingItemData?) {

        if (listingItemData != null) {
//            val name = listingItemData.name
//            val categorList = listingItemData.category.joinToString(" | ")
//            val imageUrl = listingItemData.image
//            val isVerified = listingItemData.verified

            fragmentView.displayViews(listingItemData)
        }
    }

}