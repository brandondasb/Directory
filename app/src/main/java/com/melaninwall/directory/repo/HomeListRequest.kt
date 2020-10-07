package com.melaninwall.directory.repo


import com.melaninwall.directory.interfaces.HomeListingCallback

enum class HomeScreenSection {
    RECENT, NEARME, HUNDRED
}

class HomeListRequest private constructor(builder: Builder) {

    val callback: HomeListingCallback
    val section: List<HomeScreenSection>

    init {
        callback = builder.callback
        section = builder.sections
    }

    class Builder(val callback: HomeListingCallback) {

        val sections: MutableList<HomeScreenSection> = mutableListOf()
        fun recent() = apply {
            sections.add(HomeScreenSection.RECENT)
        }

        fun nearMe() = apply {
            sections.add(HomeScreenSection.NEARME)

        }

        fun hundred() = apply {
            sections.add(HomeScreenSection.HUNDRED)

        }

        fun build() = HomeListRequest(this)
    }
}
