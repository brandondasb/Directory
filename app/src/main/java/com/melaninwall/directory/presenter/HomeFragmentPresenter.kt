package com.melaninwall.directory.presenter;

import android.text.TextWatcher
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.HomeFragmentView
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.interfaces.QuerySearchCallback
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.repo.Repo

class HomeFragmentPresenter(homeFragmentView: HomeFragmentView) :
    HomeListingCallback, CategoryListingCallBack, QuerySearchCallback {
    private val homeFragmentView = homeFragmentView

    private var textWatcher: TextWatcher? = null
    var nameList: ArrayList<String>? = null
    var categoryList: ArrayList<String>? = null
    var repo = Repo()

    override fun loadQueriedData(
        queriedListingItemData: List<ListingItemData>,
        searchText: String
    ) {
        TODO("not implemented") //An Adapter, with a setData function that takes list and search criteria
    }

    override fun loadCategoryList(categoryList: List<Category>) {
        homeFragmentView.displayCategory(categoryList)
    }

    override fun loadSectionList(listingSectionData: List<Section>) {
        homeFragmentView.displayViews(listingSectionData)
    }
}

