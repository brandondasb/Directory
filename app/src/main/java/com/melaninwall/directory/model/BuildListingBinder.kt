package com.melaninwall.directory.model

import com.melaninwall.directory.viewHolder.PageViewGenerator

class BuildListingBinder(
    val viewHolder: PageViewGenerator,
    private val dataModel: ListingItemData?
) {
    fun bind() {
        val testPattern = GetViewHolderFactory()
        testPattern.getViews(viewHolder, dataModel).getViews(dataModel)
    }
}

class GetViewHolderFactory {
    fun getViews(viewHolder: PageViewGenerator, dataModel: ListingItemData?): PageViewGenerator {
        viewHolder.getViews(dataModel)

        return viewHolder
    }
}