package com.example.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.directory.R
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.BottomNavState
import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData
import com.example.directory.presenter.HomeFragmentPresenter
import com.example.directory.repo.ListingRepo

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
        val listingRepo = ListingRepo()
        val homeFragmentPresenter = HomeFragmentPresenter(view)

        val listingCallback: ListingCallback = object : ListingCallback {
            override fun loadLondon(listingItemGroupData: List<ItemGroup>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {
                homeFragmentPresenter.loadAllGroupItemdata(listingItemGroupData)
            }
        }
        listingRepo.getAllListing(listingCallback)

    }
}