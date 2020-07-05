package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Category

interface ListItemCategoryListener {
    fun launchCategoryFragment(itemData: Category)

}