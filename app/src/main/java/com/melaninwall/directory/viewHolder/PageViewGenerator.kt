package com.melaninwall.directory.viewHolder

import com.melaninwall.directory.model.ListingItemData

interface PageViewGenerator {
    fun bindData(data: ListingItemData?)
}