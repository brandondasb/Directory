package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface ListItemCategoryListener {
    fun launchCategoryFragment(itemData: List<ListingItemData>, selectedCategory: String)

}