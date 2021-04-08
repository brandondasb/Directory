package com.melaninwall.directory.presenter;

import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.ListingSectionAdapter
import com.melaninwall.directory.interfaces.*
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.view.ResultFragment
import com.melaninwall.directory.view.SearchFragment
import com.melaninwall.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View, private var fragmentManager: FragmentManager?) :
    HomeListingCallback, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener, QuerySearchCallback {

    private val context = itemView.context
    private var textWatcher: TextWatcher? = null
    var nameList: ArrayList<String>? = null
    var categoryList: ArrayList<String>? = null
    var repo = Repo()

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
    private var listingItemGroupAdapter = ListingSectionAdapter(context, this)

    init {
        //configureSpinner()
        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        homeFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager
        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
        homeFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()

        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingItemGroupAdapter = ListingSectionAdapter(context, this)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingItemGroupAdapter


    }

    fun setAdapter(listingItemData: QuerySearchCallback, stringQuery: String) {
        repo.getQueryData(listingItemData, stringQuery)
    }


    override fun loadQueriedData(
        queriedListingItemData: List<ListingItemData>,
        searchText: String
    ) {
        TODO("not implemented") //An Adapter, with a setData function that takes list and search criteria
    }

    override fun loadCategoryList(categoryList: List<Category>) {
        categoryRecyclerViewAdapter.setData(categoryList)
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()
    }

    override fun loadSectionList(listingSectionData: List<Section>) {
        listingItemGroupAdapter.setData(listingSectionData)
        homeFragmentViewHolder.homeRecyclerView.startLayoutAnimation()
    }

    override fun launchFragment(itemData: ListingItemData) {
        val listingFragment = ListingFragment.create(itemData)

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

    override fun launchCategoryFragment(selectedCategory: String) {
        repo = Repo()
        val callBackack = object : SearchListingCallBack {
            override fun loadItemData(listingItemData: List<ListingItemData>) {
                val resultFragment = ResultFragment.create(listingItemData)

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, resultFragment)
                    ?.addToBackStack(SearchFragment::class.java.simpleName)
                    ?.commitAllowingStateLoss()
            }
        }
        repo.getListingPerCategory(callBackack, selectedCategory)
    }
}

