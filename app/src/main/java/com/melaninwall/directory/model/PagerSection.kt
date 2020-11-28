package com.melaninwall.directory.model

enum class PagerSection {
    SUMMARY, GALLERY, CONTACT
}

fun lookUpPagerSection(viewType: Int): PagerSection {
    return PagerSection.values().find {
        viewType == it.ordinal
    } ?: throw RuntimeException()
}