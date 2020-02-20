package com.example.directory.interfaces

import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData

interface ListingCallback {
    fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>)//test
    fun loadLondon(listingItemGroupData: List<ItemGroup>)
}
