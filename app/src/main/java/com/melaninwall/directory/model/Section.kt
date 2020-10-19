package com.melaninwall.directory.model

import com.melaninwall.directory.repo.HomeScreenSection

data class Section(
    val type: HomeScreenSection,
    val listItem: MutableList<ListingItemData> = mutableListOf()
) : Comparable<Section> {
    override fun compareTo(other: Section): Int = type.compareTo(other.type)
}

