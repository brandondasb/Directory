package com.melaninwall.directory.interfaces

import android.view.View
import com.melaninwall.directory.model.ListingItemData

interface ListItemListener {
    fun launchFragment(clickableView: View, itemData: ListingItemData)
}