package com.melaninwall.directory.adapters

import com.melaninwall.directory.model.Section

class HomeScreenSectionSorter : SectionSorter {
    private fun sectionTypeSort(section: Section) = section.type // 1 type of sorting

    override fun <T : Comparable<T>> sortList(
        sectionList: List<Section>,
        sortFunction: (Section) -> T
    ): List<Section> {
        return sectionList.sortedBy(::sectionTypeSort)
    }
}
//        started like this
//        this.sectionList.sortWith(Comparator { section1, section2 ->
//            section1.type.compareTo(section2.type)
//        })