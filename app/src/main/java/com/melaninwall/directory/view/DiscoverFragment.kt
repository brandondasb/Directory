package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.DiscoverListingCallBack
import com.melaninwall.directory.model.BottomNavState
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.DiscoverPresenter
import com.melaninwall.directory.repo.Repo

class DiscoverFragment : BaseNavFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavListener.updateBottomNav(BottomNavState.DISCOVER)
        val listingRepo = Repo()
        val searchFragmentPresenter = DiscoverPresenter(view, fragmentManager)

        val discoverListingCallback: DiscoverListingCallBack = object : DiscoverListingCallBack {
            override fun loadItemData(listingItemData: List<ListingItemData>) {
                searchFragmentPresenter.loadItemData(listingItemData)
            }
        }
        listingRepo.getHomeData(discoverListingCallback)

        val categoryListingCallBack: CategoryListingCallBack = object : CategoryListingCallBack {
            override fun loadItemDataCategory(categoryItemData: List<Category>) {
                searchFragmentPresenter.loadItemDataCategory(categoryItemData)
            }
        }
        listingRepo.getCategoryData(categoryListingCallBack)
    }
}