package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface QuerySearchCallback {
    fun loadQueriedData(queriedListingItemData: List<ListingItemData>,searchText: String)

}