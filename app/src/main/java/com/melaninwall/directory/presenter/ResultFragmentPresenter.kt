package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ResultFragmentView
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData

class ResultFragmentPresenter(resultFragmentView: ResultFragmentView) :
    SearchListingCallBack {
    private val resultFragmentView = resultFragmentView


    override fun loadItemData(listingItemData: List<ListingItemData>) {
        resultFragmentView.displayView(listingItemData)
    }
}