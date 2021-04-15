package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.interfaces.QuerySearchCallback
import com.melaninwall.directory.interfaces.SearchFragmentView
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData

class SearchFragmentPresenter(searchFragmentView: SearchFragmentView) :
    SearchListingCallBack,
    ListItemCategoryListener {

    //    private var textWatcher: TextWatcher? = null
    private val searchFragmentView = searchFragmentView

    override fun loadItemData(listingItemData: List<ListingItemData>) {
        searchFragmentView.displayView(listingItemData)
    }

    override fun launchCategoryFragment(
        selectedCategory: String
    ) {

    }

    fun setAdapter(listingItemData: QuerySearchCallback, stringQuery: String) {
        // repo.getQueryData(listingItemData, stringQuery)
    }

    // TODO on textChange coming soon
    fun setAfterTextChangeListener() {
    }
}