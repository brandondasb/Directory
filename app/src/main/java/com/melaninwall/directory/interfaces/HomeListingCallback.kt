package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Section

interface HomeListingCallback {
    fun loadAllGroupItemdata(listingSectionData: List<Section>)

}
