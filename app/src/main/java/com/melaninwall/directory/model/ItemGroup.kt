package com.melaninwall.directory.model

data class ItemGroup(
     val headerTitle: String = "oh oh ",
     val listItem: MutableList<ListingItemData> = mutableListOf()
)

