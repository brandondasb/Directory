package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Category

interface CategoryListingCallBack {
    fun loadCategoryList(
        categoryList: List<Category>
    )
}
