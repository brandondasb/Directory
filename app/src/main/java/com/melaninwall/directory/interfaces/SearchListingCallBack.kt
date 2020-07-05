package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface SearchListingCallBack {
    fun loadItemData(listingItemData:List<ListingItemData>)
}
