package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ItemGroup

interface HomeListingCallback {
    fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>)

}
