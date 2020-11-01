package com.melaninwall.directory.adapters

import com.melaninwall.directory.model.Section

class HomeScreenSectionSorter : SectionSorter {
    private fun sectionTypeSort(section: Section) = section.type

    override fun <T> sortList(
        sectionList: List<Section>,
        sortFunction: (Section) -> T
    ): List<Section> {
        val test = sortFunction
        val test2 = ::sectionTypeSort
        sectionList.sortedBy(test2)
        return sectionList

    }
}