package com.melaninwall.directory.adapters

import com.melaninwall.directory.model.Section

interface SectionSorter {
    fun <T> sortList(sectionList: List<Section>, sortFunction: (Section) -> T): List<Section>
}