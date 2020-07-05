package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface ListItemListener {
    fun launchFragment(itemData: ListingItemData)

}