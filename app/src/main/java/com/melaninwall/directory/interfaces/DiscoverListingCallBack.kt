package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface DiscoverListingCallBack {
    fun loadItemData(listingItemData:List<ListingItemData>)
}
