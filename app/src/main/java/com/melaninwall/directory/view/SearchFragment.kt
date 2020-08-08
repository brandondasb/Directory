package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.BottomNavState
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.SearchFragmentPresenter
import com.melaninwall.directory.repo.Repo
import java.io.Serializable

class SearchFragment : BaseNavFragment() {
    companion object {
        fun create(filteredList: List<ListingItemData>): SearchFragment {
            val bundle = Bundle()
            bundle.putSerializable(
                StorageKey.FILTERED_LIST.toString(),
                filteredList as Serializable
            )
//            bundle.putStringArrayList(StorageKey.FILTERED_LIST.toString(),filteredList)
            val fragment = SearchFragment()
            fragment.arguments = bundle
            fragment.bottomNavListener.updateBottomNav(BottomNavState.SEARCH)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavListener.updateBottomNav(BottomNavState.SEARCH)

        val listingRepo = Repo()
        val searchFragmentPresenter = SearchFragmentPresenter(view, fragmentManager)

        val searchListingCallback: SearchListingCallBack = object : SearchListingCallBack {
            override fun loadItemData(listingItemData: List<ListingItemData>) {
                searchFragmentPresenter.loadItemData(listingItemData)
            }
        }
        listingRepo.getSearchData(searchListingCallback)

        val categoryListingCallBack: CategoryListingCallBack = object : CategoryListingCallBack {
            override fun loadItemDataCategory(
                listingItemData: List<ListingItemData>,
                categoryItemData: List<Category>
            ) {
                searchFragmentPresenter.loadItemDataCategory(listingItemData, categoryItemData)
            }
        }
        listingRepo.getCategoryData(categoryListingCallBack)
    }
}