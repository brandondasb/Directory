package com.melaninwall.directory.repo


import com.melaninwall.directory.interfaces.HomeListingCallback

enum class HomeScreenSection(val title: String) {
    RECENT("Recent"),
    HUNDRED("Top 100"),
    NEARME("Near Me"),
}

class HomeListRequest private constructor(builder: Builder) {

    val callback: HomeListingCallback = builder.callback
    val section: List<HomeScreenSection>

    init {
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
