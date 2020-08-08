package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData

interface CategoryListingCallBack {
    fun loadItemDataCategory(
        listingItemData: List<ListingItemData>,
        categoryItemData: List<Category>
    )
}
