package com.example.directory.interfaces

import com.example.directory.model.ListingItemData

interface ListingCallback {
    fun loadAllListingItemData(listingItemData: List<ListingItemData>)
}