package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Section

interface HomeListingCallback {
    fun loadSectionList(listingSectionData: List<Section>)

}
