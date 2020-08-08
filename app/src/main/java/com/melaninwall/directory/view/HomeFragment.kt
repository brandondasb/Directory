package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.model.BottomNavState
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ItemGroup
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.HomeFragmentPresenter
import com.melaninwall.directory.repo.Repo


class HomeFragment : BaseNavFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavListener.updateBottomNav(BottomNavState.HOME)
        val listingRepo = Repo()
        val homeFragmentPresenter = HomeFragmentPresenter(view, fragmentManager)

        val homeListingCallback: HomeListingCallback = object : HomeListingCallback {

            override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {
                homeFragmentPresenter.loadAllGroupItemdata(listingItemGroupData)
            }
        }
        listingRepo.getAllListing(homeListingCallback)
        val categoryListingCallBack: CategoryListingCallBack = object : CategoryListingCallBack {
            override fun loadItemDataCategory(
                listingItemData: List<ListingItemData>,
                categoryItemData: List<Category>
            ) {
                homeFragmentPresenter.loadItemDataCategory(listingItemData, categoryItemData)
            }
        }
        listingRepo.getCategoryData(categoryListingCallBack)
    }

    //listingRepo.addListing()//TODO  used for testing purposes, helps create sample data.
}
